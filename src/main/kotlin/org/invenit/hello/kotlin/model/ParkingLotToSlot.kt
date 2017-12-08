package org.invenit.hello.kotlin.model

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
class ParkingLotToSlot(override var id: Int?, val lotId: Int, val slotId: Int) : Entity {
    constructor(lotId: Int, slotId: Int): this(null, lotId, slotId)
}