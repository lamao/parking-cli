package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.ParkingSlotRent

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotRentRepository : AbstractCrudRepository<ParkingSlotRent>("parking_slot_rent", ParkingSlotRent::class.java)