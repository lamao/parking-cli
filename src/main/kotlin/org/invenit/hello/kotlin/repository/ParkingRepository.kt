package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Parking

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object ParkingRepository : AbstractCrudRepository<Parking>("parking", Parking::class.java)