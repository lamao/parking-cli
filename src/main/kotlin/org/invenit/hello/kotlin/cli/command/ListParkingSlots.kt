package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.repository.ParkingLotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListParkingSlots : Command {
    override fun execute(args: List<String>) {
        val parkingId: Int
        if (args.isEmpty()) {
            print("Parking lot ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Required")
        } else {
            parkingId = args[0].toInt()
        }

        val parking = ParkingLotRepository.get(parkingId) ?: throw IllegalArgumentException("Parking lot #$parkingId not found")
        for (slot in parking.slots) {
            print("#${slot.id}. Price: ${slot.price}. ")
            if (!slot.description.isBlank()) {
                print(slot.description)
            }
            println()
        }
        println()
        println("Total: ${parking.slots.size} items")
    }
}