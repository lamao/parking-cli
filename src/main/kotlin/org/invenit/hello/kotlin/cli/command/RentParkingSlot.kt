package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.Car
import org.invenit.hello.kotlin.repository.CarRepository
import org.invenit.hello.kotlin.repository.RentRepository
import org.invenit.hello.kotlin.service.ParkingSlotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class RentParkingSlot :Command {
    override val description: String
        get() = "Mark slot as occupied"

    override fun execute(args: List<String>) {
        val parkingId : Int
        if (args.isEmpty()) {
            print("Parking ID: ")
            parkingId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            parkingId = args[0].toInt()
        }

        val slotId: Int
        if (args.size < 2) {
            print("Slot ID: ")
            slotId = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        } else {
            slotId = args[1].toInt()
        }

        val car = getCar()

        ParkingSlotService.rent(parkingId, slotId, car.id)
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
            val rentSlots = RentRepository.getWhere { it.carId == car.id }
            if (rentSlots.isNotEmpty()) {
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