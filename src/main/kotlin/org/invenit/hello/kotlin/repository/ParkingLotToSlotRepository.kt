package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.ParkingToSpot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */

object ParkingLotToSlotRepository : AbstractCrudRepository<ParkingToSpot>("parking_lot_to_slot", ParkingToSpot::class.java)
