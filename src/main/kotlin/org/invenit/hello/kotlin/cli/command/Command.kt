package org.invenit.hello.kotlin.cli.command

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
interface Command {
    val description:String

    fun execute(args: List<String>)
}