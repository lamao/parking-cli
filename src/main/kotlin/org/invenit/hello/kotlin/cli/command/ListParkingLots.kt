package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.repository.ParkingRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListParkingLots : Command {
    override val description: String
        get() = "Print all parking lots"

    override fun execute(args: List<String>) {
        val entities = ParkingRepository.getAll()
        for (entity in entities) {
            println("#${entity.id}. Name: ${entity.name}")
        }

        println()
        println("Total: ${entities.size} items")
    }
}