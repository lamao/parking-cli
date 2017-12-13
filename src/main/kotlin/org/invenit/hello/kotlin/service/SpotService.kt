package org.invenit.hello.kotlin.service

import org.invenit.hello.kotlin.model.ParkingToSpot
import org.invenit.hello.kotlin.model.Spot
import org.invenit.hello.kotlin.model.Rent
import org.invenit.hello.kotlin.repository.ParkingRepository
import org.invenit.hello.kotlin.repository.ParkingToSpotRepository
import org.invenit.hello.kotlin.repository.RentRepository
import org.invenit.hello.kotlin.repository.SpotRepository

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
object SpotService {

    private val parkingRepository = ParkingRepository
    private val parkingToSpotRepository = ParkingToSpotRepository
    private val spotRepository = SpotRepository
    private val rentRepository = RentRepository

    fun save(parkingId: Int, spot: Spot): Spot {
        if (!parkingRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val createdSpot = spotRepository.save(spot)
        parkingToSpotRepository.save(ParkingToSpot(parkingId, createdSpot.id))
        return createdSpot
    }

    fun findAll(parkingId: Int): List<Spot> {
        if (!parkingRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return parkingToSpotRepository
                .getWhere { it.parkingId == parkingId }
                .mapNotNull { spotRepository.get(it.spotId) }
    }

    fun rent(parkingId: Int, spotId: Int, carId: Int) {
        if (!parkingRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val spot = parkingToSpotRepository.getWhere { it.parkingId == parkingId && it.spotId == spotId }
        if (spot.isEmpty()) {
            throw IllegalArgumentException("Spot $spotId not found for parking $parkingId")
        }

        val rent = rentRepository.getWhere { it.spotId == spotId }
        if (!rent.isEmpty()) {
            throw IllegalArgumentException("Spot $spotId already occupied")
        }

        rentRepository.save(Rent(spotId, carId))
    }

    fun release(parkingId: Int, spotId: Int) {
        if (!parkingRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }
        val spot = parkingToSpotRepository.getWhere { it.parkingId == parkingId && it.spotId == spotId }
        if (spot.isEmpty()) {
            throw IllegalArgumentException("Spot $spotId not found for parking $parkingId")
        }

        rentRepository.deleteWhere { it.spotId == spotId }
    }

    fun findFree(parkingId: Int): List<Spot> {
        if (!parkingRepository.exists(parkingId)) {
            throw IllegalArgumentException("Parking $parkingId not found")
        }

        return parkingToSpotRepository
                .getWhere { it.parkingId == parkingId }
                .filter { parkingToSpot -> rentRepository.getWhere { it.spotId == parkingToSpot.spotId }.isEmpty() }
                .mapNotNull { spotRepository.get(it.spotId) }
    }
}