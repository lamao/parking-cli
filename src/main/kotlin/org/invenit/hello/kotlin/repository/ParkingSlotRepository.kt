package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Spot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingSlotRepository : AbstractCrudRepository<Spot>("parking_slot", Spot::class.java)
