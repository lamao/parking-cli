package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.ParkingLotToSlot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */

object ParkingLotToSlotRepository : AbstractCrudRepository<ParkingLotToSlot>("parking_lot_to_slot", ParkingLotToSlot::class.java)
