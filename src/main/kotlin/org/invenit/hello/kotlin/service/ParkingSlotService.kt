package org.invenit.hello.kotlin.service

import org.invenit.hello.kotlin.model.ParkingSlot
import org.invenit.hello.kotlin.repository.ParkingLotRepository
import org.invenit.hello.kotlin.repository.ParkingSlotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotService {
    fun save(parkingId: Int, slot: ParkingSlot): ParkingSlot {
        val parking = ParkingLotRepository.get(parkingId) ?: throw IllegalArgumentException("Parking not found")
        val createdSlot = ParkingSlotRepository.save(slot)
        parking.slots.add(createdSlot)
        return createdSlot
    }
}