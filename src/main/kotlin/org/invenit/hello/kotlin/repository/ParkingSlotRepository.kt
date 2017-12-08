package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.ParkingSlot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotRepository : AbstractCrudRepository<ParkingSlot>("parking_slot", ParkingSlot::class.java)
