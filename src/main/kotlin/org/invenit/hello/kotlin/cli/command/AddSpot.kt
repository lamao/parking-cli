package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService
import org.invenit.hello.kotlin.model.Spot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddSpot : Command {
    override val description: String
        get() = "Add new spot to parking"

    override fun execute(args: List<String>) {
        val parkingId: Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Required")
        } else {
            parkingId = args[0].toInt()
        }

        print("Price: ")
        val price = readLine()?.toDouble() ?: throw IllegalArgumentException("Wrong format")
        print("Description (optional): ")
        val description = readLine() ?: ""

        val spot = SpotService.save(parkingId, Spot(price, description))
        println("Parking spot created: #${spot.id}")
    }
}