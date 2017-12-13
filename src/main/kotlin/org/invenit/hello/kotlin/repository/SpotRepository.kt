package org.invenit.hello.kotlin.repository

import org.invenit.hello.kotlin.model.Spot

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object SpotRepository : AbstractCrudRepository<Spot>("spot", Spot::class.java)
