package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ParkingSlotRent(
        val slotId: Int,
        val carId: Int,
        override var id: Int = 0
) : Entity