package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.ParkingLot
import org.invenit.hello.kotlin.repository.ParkingLotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddParkingLot : Command {
    override fun execute(args: List<String>) {
        print("Name: ")
        val name = readLine() ?: ""
        val createdParking = ParkingLotRepository.save(ParkingLot(name))
        println("Parking lot created: #${createdParking.id}")
    }
}