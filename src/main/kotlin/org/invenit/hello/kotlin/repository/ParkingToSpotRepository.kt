package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.ParkingToSpot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */

object ParkingToSpotRepository : AbstractCrudRepository<ParkingToSpot>("parking_to_spot", ParkingToSpot::class.java)
