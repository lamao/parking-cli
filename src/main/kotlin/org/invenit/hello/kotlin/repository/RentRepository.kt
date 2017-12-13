package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Rent

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object RentRepository : AbstractCrudRepository<Rent>("rent", Rent::class.java)