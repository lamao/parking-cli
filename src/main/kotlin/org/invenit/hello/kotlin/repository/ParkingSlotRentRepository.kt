package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Rent

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotRentRepository : AbstractCrudRepository<Rent>("parking_slot_rent", Rent::class.java)