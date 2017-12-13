package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService
import org.invenit.hello.kotlin.model.Spot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddParkingSlot : Command {
    override val description: String
        get() = "Add new slot to parking lot"

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

        val slot = SpotService.save(parkingId, Spot(price, description))
        println("Parking slot created: #${slot.id}")
    }
}