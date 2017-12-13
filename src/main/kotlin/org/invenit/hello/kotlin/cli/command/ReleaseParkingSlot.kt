package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ReleaseParkingSlot : Command {
    override val description: String
        get() = "Mark slot as free"

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

        SpotService.release(parkingId, slotId)
    }
}