package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ReleaseSpot : Command {
    override val description: String
        get() = "Mark spot as free"

    override fun execute(args: List<String>) {
        val parkingId : Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            parkingId = args[0].toInt()
        }

        val spotId: Int
        if (args.size < 2) {
            print("Spot ID: ")
            spotId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            spotId = args[1].toInt()
        }

        SpotService.release(parkingId, spotId)
    }
}