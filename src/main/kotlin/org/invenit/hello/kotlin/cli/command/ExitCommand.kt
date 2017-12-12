package org.invenit.hello.kotlin.cli.command

import kotlin.system.exitProcess

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ExitCommand : Command {
    override val description: String
        get() = "Exit CLI"

    override fun execute(args: List<String>) {
        exitProcess(0)
    }
}