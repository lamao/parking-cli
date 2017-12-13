package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
data class Spot(
        var price: Double,
        var description: String = "",
        override var id: Int = 0
) : Entity