package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.repository.CarRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ListCars : Command {
    override val description: String
        get() = "List all registered cars"

    override fun execute(args: List<String>) {
        val cars = CarRepository.getAll()
        for (car in cars) {
            println("#${car.id}. Number: ${car.number}. Model: ${car.model}")
        }
        println("Total: ${cars.size} items")
    }
}
