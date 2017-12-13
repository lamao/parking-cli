package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.Parking
import org.invenit.hello.kotlin.repository.ParkingRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddParkingLot : Command {
    override val description: String
        get() = "Add new parking lot"

    override fun execute(args: List<String>) {
        print("Name: ")
        val name = readLine() ?: ""
        val createdParking = ParkingRepository.save(Parking(name))
        println("Parking lot created: #${createdParking.id}")
    }
}