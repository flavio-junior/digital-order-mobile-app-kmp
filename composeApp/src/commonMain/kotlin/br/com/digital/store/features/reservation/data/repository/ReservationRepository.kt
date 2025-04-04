package br.com.digital.store.features.reservation.data.repository

import br.com.digital.store.features.networking.resources.ObserveNetworkStateHandler
import br.com.digital.store.features.reservation.data.dto.EditReservationRequestDTO
import br.com.digital.store.features.reservation.data.dto.GenerateReservationsRequestVO
import br.com.digital.store.features.reservation.data.dto.ReservationRequestDTO
import br.com.digital.store.features.reservation.data.dto.ReservationResponseDTO
import br.com.digital.store.features.reservation.data.dto.ReservationsResponseDTO
import br.com.digital.store.utils.CommonUtils.EMPTY_TEXT
import br.com.digital.store.utils.NumbersUtils.NUMBER_SIXTY
import br.com.digital.store.utils.NumbersUtils.NUMBER_ZERO
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    fun findAllReservations(
        name: String = EMPTY_TEXT,
        page: Int = NUMBER_ZERO,
        size: Int = NUMBER_SIXTY,
        sort: String
    ): Flow<ObserveNetworkStateHandler<ReservationsResponseDTO>>
    fun finReservationByName(name: String): Flow<ObserveNetworkStateHandler<List<ReservationResponseDTO>>>
    fun createNewReservation(reservation: ReservationRequestDTO): Flow<ObserveNetworkStateHandler<Unit>>
    fun generateReservations(body: GenerateReservationsRequestVO): Flow<ObserveNetworkStateHandler<Unit>>
    fun editReservation(reservation: EditReservationRequestDTO): Flow<ObserveNetworkStateHandler<Unit>>
    fun deleteReservation(id: Long): Flow<ObserveNetworkStateHandler<Unit>>
}
