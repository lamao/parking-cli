package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
data class ParkingLot(
        override var id: Int?,
        var name: String
) : Entity {
    constructor(name: String) : this(null, name)
}