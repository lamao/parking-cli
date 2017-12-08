package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
data class ParkingSlot(
        override var id: Int?,
        var price: Double,
        var description: String = ""
) : Entity {
    constructor(price: Double, description: String = "") : this(null, price, description)
}