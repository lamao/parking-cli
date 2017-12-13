package org.invenit.hello.kotlin.service

import org.invenit.hello.kotlin.model.ParkingLotToSlot
import org.invenit.hello.kotlin.model.ParkingSlot
import org.invenit.hello.kotlin.model.ParkingSlotRent
import org.invenit.hello.kotlin.repository.ParkingLotRepository
import org.invenit.hello.kotlin.repository.ParkingLotToSlotRepository
import org.invenit.hello.kotlin.repository.ParkingSlotRentRepository
import org.invenit.hello.kotlin.repository.ParkingSlotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotService {

    private val lotRepository = ParkingLotRepository
    private val lotToSlotRepository = ParkingLotToSlotRepository
    private val slotRepository = ParkingSlotRepository
    private val slotRentRepository = ParkingSlotRentRepository

    fun save(parkingId: Int, slot: ParkingSlot): ParkingSlot {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val createdSlot = slotRepository.save(slot)
        lotToSlotRepository.save(ParkingLotToSlot(parkingId, createdSlot.id))
        return createdSlot
    }

    fun findAll(parkingId: Int): List<ParkingSlot> {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return lotToSlotRepository
                .getWhere { it.lotId == parkingId }
                .mapNotNull { slotRepository.get(it.slotId) }
    }

    fun rent(parkingId: Int, slotId: Int, carId: Int) {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val slot = lotToSlotRepository.getWhere { it.lotId == parkingId && it.slotId == slotId }
        if (slot.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId not found for parking $parkingId")
        }

        val rent = slotRentRepository.getWhere { it.slotId == slotId }
        if (!rent.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId already occupied")
        }


        slotRentRepository.save(ParkingSlotRent(slotId, carId))
    }

    fun release(parkingId: Int, slotId: Int) {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val slot = lotToSlotRepository.getWhere { it.lotId == parkingId && it.slotId == slotId }
        if (slot.isEmpty()) {
            throw IllegalArgumentException("Slot $slotId not found for parking $parkingId")
        }

        slotRentRepository.deleteWhere { it.slotId == slotId }
    }

    fun findFree(parkingId: Int): List<ParkingSlot> {
        if (!lotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return lotToSlotRepository
                .getWhere { it.lotId == parkingId }
                .filter { lotToSlot -> slotRentRepository.getWhere { it.slotId == lotToSlot.slotId }.isEmpty() }
                .mapNotNull { slotRepository.get(it.slotId) }
    }
}