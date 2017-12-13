package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.Car
import org.invenit.hello.kotlin.repository.CarRepository
import org.invenit.hello.kotlin.repository.RentRepository
import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class RentSpot :Command {
    override val description: String
        get() = "Mark spot as occupied"

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

        val car = getCar()

        SpotService.rent(parkingId, spotId, car.id)
    }

    private fun getCar(): Car {
        print("Car number: ")
        val number = readLine().orEmpty()
        if (number.isBlank()) {
            throw IllegalArgumentException("Wrong number")
        }
        val foundCars = CarRepository.getWhere { it.number == number }

        val car: Car
        if (foundCars.isNotEmpty()) {
            car = foundCars.first()
            val rentSpots = RentRepository.getWhere { it.carId == car.id }
            if (rentSpots.isNotEmpty()) {
                throw IllegalArgumentException("Car is already parked")
            }
        } else {
            println("Car not found. Registering new car")
            print("Car model: ")
            val model = readLine().orEmpty()
            if (model.isBlank()) {
                throw IllegalArgumentException("Wrong model")
            }
            car = CarRepository.save(Car(number, model))
            println("Created car #${car.id}")
        }
        return car
    }
}