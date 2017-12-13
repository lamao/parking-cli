package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.ParkingSlotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class SearchParkingSlot : Command {
    override val description: String
        get() = "Search free parking slot"

    override fun execute(args: List<String>) {
        val parkingId : Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            parkingId = args[0].toInt()
        }

        val slots = ParkingSlotService.findFree(parkingId)
        for (slot in slots) {
            print("#${slot.id}. Price: ${slot.price}.")
            if (slot.description.isNotBlank()) {
                print(" Description: ${slot.description}")
            }
            println()
        }
        println("Total: ${slots.size} items")
    }
}