package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.ParkingSlotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class RentParkingSlot :Command {
    override val description: String
        get() = "Mark slot as occupied"

    override fun execute(args: List<String>) {
        val parkingId : Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            parkingId = args[0].toInt()
        }

        val slotId: Int
        if (args.size < 2) {
            print("Slot ID: ")
            slotId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            slotId = args[1].toInt()
        }

        ParkingSlotService.rent(parkingId, slotId)
    }
}