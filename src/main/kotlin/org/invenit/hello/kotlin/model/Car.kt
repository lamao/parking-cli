package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class Car (
        val number: String,
        val model: String,
        override var id: Int = 0
) : Entity