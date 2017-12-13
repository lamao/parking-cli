package org.invenit.hello.kotlin.service

import org.invenit.hello.kotlin.model.ParkingToSpot
import org.invenit.hello.kotlin.model.Spot
import org.invenit.hello.kotlin.model.Rent
import org.invenit.hello.kotlin.repository.ParkingRepository
import org.invenit.hello.kotlin.repository.ParkingToSpotRepository
import org.invenit.hello.kotlin.repository.RentRepository
import org.invenit.hello.kotlin.repository.SpotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotService {

    private val lotRepository = ParkingRepository
    private val lotToSlotRepository = ParkingToSpotRepository
    private val slotRepository = SpotRepository
    private val slotRentRepository = RentRepository

    fun save(parkingId: Int, slot: Spot): Spot {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val createdSlot = slotRepository.save(slot)
        lotToSlotRepository.save(ParkingToSpot(parkingId, createdSlot.id))
        return createdSlot
    }

    fun findAll(parkingId: Int): List<Spot> {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return lotToSlotRepository
                .getWhere { it.parkingId == parkingId }
                .mapNotNull { slotRepository.get(it.spotId) }
    }

    fun rent(parkingId: Int, slotId: Int, carId: Int) {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val slot = lotToSlotRepository.getWhere { it.parkingId == parkingId && it.spotId == slotId }
        if (slot.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId not found for parking $parkingId")
        }

        val rent = slotRentRepository.getWhere { it.spotId == slotId }
        if (!rent.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId already occupied")
        }


        slotRentRepository.save(Rent(slotId, carId))
    }

    fun release(parkingId: Int, slotId: Int) {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val slot = lotToSlotRepository.getWhere { it.parkingId == parkingId && it.spotId == slotId }
        if (slot.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId not found for parking $parkingId")
        }

        slotRentRepository.deleteWhere { it.spotId == slotId }
    }

    fun findFree(parkingId: Int): List<Spot> {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return lotToSlotRepository
                .getWhere { it.parkingId == parkingId }
                .filter { lotToSlot -> slotRentRepository.getWhere { it.spotId == lotToSlot.spotId }.isEmpty() }
                .mapNotNull { slotRepository.get(it.spotId) }
    }
}