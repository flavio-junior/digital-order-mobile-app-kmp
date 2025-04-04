package br.com.digital.store.features.order.data.repository

import br.com.digital.store.features.networking.resources.ObserveNetworkStateHandler
import br.com.digital.store.features.order.data.dto.AddressRequestDTO
import br.com.digital.store.features.order.data.dto.ObjectRequestDTO
import br.com.digital.store.features.order.data.dto.OrderRequestDTO
import br.com.digital.store.features.order.data.dto.OrderResponseDTO
import br.com.digital.store.features.order.data.dto.OrdersResponseDTO
import br.com.digital.store.features.order.data.dto.PaymentRequestDTO
import br.com.digital.store.features.order.data.dto.UpdateObjectRequestDTO
import br.com.digital.store.features.order.data.dto.UpdateStatusDeliveryRequestDTO
import br.com.digital.store.features.reservation.data.dto.ReservationResponseDTO
import br.com.digital.store.utils.NumbersUtils.NUMBER_SIXTY
import br.com.digital.store.utils.NumbersUtils.NUMBER_ZERO
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun findAllOpenOrders(
        page: Int = NUMBER_ZERO,
        size: Int = NUMBER_SIXTY,
        sort: String
    ): Flow<ObserveNetworkStateHandler<OrdersResponseDTO>>

    fun createNewOrder(order: OrderRequestDTO): Flow<ObserveNetworkStateHandler<OrderResponseDTO>>

    fun updateOrder(
        orderId: Long,
        objectId: Long,
        updateObject: UpdateObjectRequestDTO
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun updateAddressOrder(
        orderId: Long,
        addressId: Long,
        updateAddress: AddressRequestDTO
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun updateStatusDelivery(
        orderId: Long,
        status: UpdateStatusDeliveryRequestDTO
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun incrementMoreObjectsOrder(
        orderId: Long,
        incrementObjects: List<ObjectRequestDTO>
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun incrementMoreReservationsOrder(
        orderId: Long,
        reservationsToSava: List<ReservationResponseDTO>
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun removeReservationOrder(
        orderId: Long,
        reservationId: Long
    ): Flow<ObserveNetworkStateHandler<Unit>>

    fun deleteOrder(id: Long): Flow<ObserveNetworkStateHandler<Unit>>

    fun closeOrder(
        orderId: Long,
        force: Boolean = false,
        payment: PaymentRequestDTO
    ): Flow<ObserveNetworkStateHandler<OrderResponseDTO>>
}
