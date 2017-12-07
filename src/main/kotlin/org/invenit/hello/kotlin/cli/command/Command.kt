package org.invenit.hello.kotlin.cli.command

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
interface Command {
    fun execute(args: List<String>)
}