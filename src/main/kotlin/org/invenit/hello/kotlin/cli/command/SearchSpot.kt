package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class SearchSpot : Command {
    override val description: String
        get() = "Search free parking spot"

    override fun execute(args: List<String>) {
        val parkingId : Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            parkingId = args[0].toInt()
        }

        val spots = SpotService.findFree(parkingId)
        for (spot in spots) {
            print("#${spot.id}. Price: ${spot.price}.")
            if (spot.description.isNotBlank()) {
                print(" Description: ${spot.description}")
            }
            println()
        }
        println("Total: ${spots.size} items")
    }
}