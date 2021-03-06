package org.invenit.hello.kotlin

import org.invenit.hello.kotlin.cli.CommandStatus
import org.invenit.hello.kotlin.cli.Dispatcher
import org.invenit.hello.kotlin.model.Parking
import org.invenit.hello.kotlin.model.Spot
import org.invenit.hello.kotlin.repository.ParkingRepository
import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
fun main(args: Array<String>) {
    preset()

    println("Welcome to Parking CLI")
    println("Type 'help' to get list of command")
    println()
    println("List of existed parkings: ")
    Dispatcher.execute("lot.list")
    do {
        print("> ")
        val command = readLine() ?: ""
        val status = Dispatcher.execute(command)
        if (status == CommandStatus.NOT_FOUND) {
            println("Command not found")
        }
    } while (true)

}

fun preset() {
    run {
        val parking = ParkingRepository.save(Parking("One"))
        val parkingId = parking.id
        for (i in 0..5) {
            SpotService.save(parkingId, Spot(10.0))
        }

        for (i in 0..2) {
            SpotService.save(parkingId, Spot(15.0, "extended"))
        }

        for (i in 0..1) {
            SpotService.save(parkingId, Spot(20.0, "with roof"))
        }
    }

    run {
        val parking = ParkingRepository.save(Parking("Two"))
        val parkingId = parking.id
        for (i in 0..4) {
            SpotService.save(parkingId, Spot(10.0))
        }

        for (i in 0..3) {
            SpotService.save(parkingId, Spot(15.0, "extended"))
        }

        for (i in 0..2) {
            SpotService.save(parkingId, Spot(20.0, "with roof"))
        }
    }
}