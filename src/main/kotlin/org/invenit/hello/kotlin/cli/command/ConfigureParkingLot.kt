package org.invenit.hello.kotlin.cli.command

import org.invenit.hello.kotlin.model.ParkingLot
import org.invenit.hello.kotlin.model.ParkingSlot
import org.invenit.hello.kotlin.repository.ParkingLotRepository
import org.invenit.hello.kotlin.service.ParkingSlotService

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ConfigureParkingLot : Command {
    private val slotModes = arrayOf("1", "2", "0")

    override fun execute(args: List<String>) {
        println("You are now in Configuration Wizard.")
        print("Name: ")
        val name = readLine() ?: ""
        val parkingLot = ParkingLot(name)

        val slots = mutableListOf<ParkingSlot>()
        println("Choose how to add slots. ")
        do {
            print("Batch mode (1), individual mode (2) or go next step (0): ")
            val choice = readLine()
            if (choice in slotModes) {
                if (choice == "1") {
                    slots.addAll(addSlotsInBatchMode())
                } else if (choice == "2") {
                    slots.add(addSlotInIndividualMode())
                }
            } else {
                println("Invalid option")
            }
        } while (choice != "0")

        val createdParkingLog = ParkingLotRepository.save(parkingLot)
        for (slot in slots) {
            ParkingSlotService.save(createdParkingLog.id!!, slot)
        }
        println("Created parking lot #${parkingLot.id} with ${slots.size} slots")
    }

    // TODO Move to common logic. This and @link AddParkingSlot
    private fun addSlotInIndividualMode(): ParkingSlot {
        print("Price: ")
        val price = readLine()?.toDouble() ?: throw IllegalArgumentException("Wrong format")
        print("Description (optional): ")
        val description = readLine() ?: ""

        return ParkingSlot(price, description)
    }

    private fun addSlotsInBatchMode(): Collection<ParkingSlot> {
        val result = mutableListOf<ParkingSlot>()

        print("Enter number of slots: ")
        val numberOfSlots = readLine()?.toInt() ?: throw IllegalArgumentException("Wrong format")
        val prototype = addSlotInIndividualMode()

        for (i in 1..numberOfSlots) {
            result.add(prototype.copy())
        }

        return result
    }


}