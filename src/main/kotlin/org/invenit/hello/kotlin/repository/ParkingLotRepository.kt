package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Parking

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingLotRepository : AbstractCrudRepository<Parking>("parking_lot", Parking::class.java)