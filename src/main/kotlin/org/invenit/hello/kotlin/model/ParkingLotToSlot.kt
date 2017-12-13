package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ParkingLotToSlot(
        val lotId: Int,
        val slotId: Int,
        override var id: Int = 0
) : Entity