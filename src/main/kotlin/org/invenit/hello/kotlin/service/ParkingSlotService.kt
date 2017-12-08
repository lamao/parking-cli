package org.invenit.hello.kotlin.service

import org.invenit.hello.kotlin.model.ParkingLotToSlot
import org.invenit.hello.kotlin.model.ParkingSlot
import org.invenit.hello.kotlin.repository.ParkingLotRepository
import org.invenit.hello.kotlin.repository.ParkingLotToSlotRepository
import org.invenit.hello.kotlin.repository.ParkingSlotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotService {
    fun save(parkingId: Int, slot: ParkingSlot): ParkingSlot {
        if (!ParkingLotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking not found")
        }
        val createdSlot = ParkingSlotRepository.save(slot)
        ParkingLotToSlotRepository.save(ParkingLotToSlot(parkingId, createdSlot.id!!))
        return createdSlot
    }

    fun findAll(parkingId: Int): List<ParkingSlot> {
        if (!ParkingSlotRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking not found")
        }

        return ParkingLotToSlotRepository
                .getWhere { it.lotId == parkingId }
                .mapNotNull { ParkingSlotRepository.get(it.slotId) }
    }
}