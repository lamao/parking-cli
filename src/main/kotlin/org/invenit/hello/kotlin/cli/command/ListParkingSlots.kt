package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListParkingSlots : Command {
    override val description: String
        get() = "Print all slots for given parking lot"

    override fun execute(args: List<String>) {
        val parkingId: Int
        if (args.isEmpty()) {
            print("Parking lot ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Required")
        } else {
            parkingId = args[0].toInt()
        }

        val slots = SpotService.findAll(parkingId)
        for (slot in slots) {
            print("#${slot.id}. Price: ${slot.price}. ")
            if (!slot.description.isBlank()) {
                print(slot.description)
            }
            println()
        }
        println()
        println("Total: ${slots.size} items")
    }
}