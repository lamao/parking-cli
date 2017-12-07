package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.ParkingSlotService
import org.invenit.hello.kotlin.model.ParkingSlot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddParkingSlot : Command {
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

        val slot = ParkingSlotService.save(parkingId, ParkingSlot(price, description))
        println("Parking slot created: #${slot.id}")
    }
}