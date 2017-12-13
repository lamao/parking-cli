package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
data class ParkingLot(
        var name: String,
        override var id: Int = 0
) : Entity