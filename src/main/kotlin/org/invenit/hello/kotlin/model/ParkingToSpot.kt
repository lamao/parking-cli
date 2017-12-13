package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ParkingToSpot(
        val parkingId: Int,
        val spotId: Int,
        override var id: Int = 0
) : Entity