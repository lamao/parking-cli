package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.repository.ParkingLotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListParkingLots : Command {

    override fun execute(args: List<String>) {
        val entities = ParkingLotRepository.getAll()
        for (entity in entities) {
            println("#${entity.id}. Name: ${entity.name}")
        }

        println()
        println("Total: ${entities.size} items")
    }
}