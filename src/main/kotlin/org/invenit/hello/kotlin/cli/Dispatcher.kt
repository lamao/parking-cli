package org.invenit.hello.kotlin.cli

import org.invenit.hello.kotlin.cli.command.*

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object Dispatcher {

    private val commandDelimiter = ' '

    private val commands: Map<String, Command> = mapOf(
            Pair("exit", ExitCommand()),
            Pair("lot.add", AddParkingLot()),
            Pair("lot.list", ListParkingLots()),
            Pair("slot.add", AddParkingSlot()),
            Pair("slot.list", ListParkingSlots()),
            Pair("slot.rent", RentParkingSlot()),
            Pair("slot.release", ReleaseParkingSlot()),
            Pair("slot.search", SearchParkingSlot()),
            Pair("lot.configure", ConfigureParkingLot()),
            Pair("help", HelpCommand())
    ).toSortedMap()

    fun execute(command: String): CommandStatus {
        val args = command.trim().split(commandDelimiter)
        if (args.isEmpty()) {
            return CommandStatus.NOT_FOUND
        }

        val executor = commands[args.first()] ?: return CommandStatus.NOT_FOUND

        try {
            executor.execute(args.subList(1, args.size))
        } catch (e: Throwable) {
            e.printStackTrace()
            return CommandStatus.FAILURE
        }
        return CommandStatus.SUCCESS
    }

    private class HelpCommand : Command {
        override val description: String
            get() = "Print list of available commands"

        override fun execute(args: List<String>) {
            println("List of available commands")
            for (command in commands) {
                println("    ${command.key} - ${command.value.description}")
            }
        }
    }
}