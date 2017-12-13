package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.Parking
import org.invenit.hello.kotlin.model.Spot
import org.invenit.hello.kotlin.repository.ParkingRepository
import org.invenit.hello.kotlin.service.SpotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ConfigureParking : Command {
    // TODO Refactor
    private val spotModes = arrayOf("1", "2", "0")

    override val description: String
        get() = "Configuration wizard for entire parking with spots"

    override fun execute(args: List<String>) {
        println("You are now in Configuration Wizard.")
        print("Name: ")
        val name = readLine() ?: ""
        val parking = Parking(name)

        val spots = mutableListOf<Spot>()
        println("Choose how to add spots. ")
        do {
            print("Batch mode (1), individual mode (2) or go next step (0): ")
            val choice = readLine()
            if (choice in spotModes) {
                if (choice == "1") {
                    spots.addAll(addSpotsInBatchMode())
                } else if (choice == "2") {
                    spots.add(addSpotInIndividualMode())
                }
            } else {
                println("Invalid option")
            }
        } while (choice != "0")

        val createdParking = ParkingRepository.save(parking)
        for (spot in spots) {
            SpotService.save(createdParking.id, spot)
        }
        println("Created parking #${parking.id} with ${spots.size} spots")
    }

    // TODO Move to common logic. This and AddParkingSpot
    private fun addSpotInIndividualMode(): Spot {
        print("Price: ")
        val price = readLine()?.toDouble() ?: throw IllegalArgumentException("Wrong format")
        print("Description (optional): ")
        val description = readLine() ?: ""

        return Spot(price, description)
    }

    private fun addSpotsInBatchMode(): Collection<Spot> {
        val result = mutableListOf<Spot>()

        print("Enter number of spots: ")
        val numberOfSpots = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        val prototype = addSpotInIndividualMode()

        for (i in 1..numberOfSpots) {
            result.add(prototype.copy())
        }

        return result
    }


}