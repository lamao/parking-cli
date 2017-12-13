package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.Parking
import org.invenit.hello.kotlin.repository.ParkingRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class AddParking : Command {
    override val description: String
        get() = "Add new parking"

    override fun execute(args: List<String>) {
        print("Name: ")
        val name = readLine() ?: ""
        val createdParking = ParkingRepository.save(Parking(name))
        println("Parking created: #${createdParking.id}")
    }
}