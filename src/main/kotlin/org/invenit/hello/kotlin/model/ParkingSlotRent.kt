package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ParkingSlotRent(override var id: Int?, val slotId: Int) : Entity {
    constructor(slotId: Int): this(null, slotId)
}