package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Car

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object CarRepository : AbstractCrudRepository<Car>("car", Car::class.java)