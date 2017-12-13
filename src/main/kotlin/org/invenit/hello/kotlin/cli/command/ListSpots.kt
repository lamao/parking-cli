package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListSpots : Command {
    override val description: String
        get() = "Print all spots for given parking"

    override fun execute(args: List<String>) {
        val parkingId: Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Required")
        } else {
            parkingId = args[0].toInt()
        }

        val spots = SpotService.findAll(parkingId)
        for (spot in spots) {
            print("#${spot.id}. Price: ${spot.price}. ")
            if (!spot.description.isBlank()) {
                print(spot.description)
            }
            println()
        }
        println()
        println("Total: ${spots.size} items")
    }
}