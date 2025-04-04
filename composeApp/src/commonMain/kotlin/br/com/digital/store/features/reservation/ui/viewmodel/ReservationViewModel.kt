package br.com.digital.store.features.reservation.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digital.store.components.strings.StringsUtils.ASC
import br.com.digital.store.features.networking.resources.ObserveNetworkStateHandler
import br.com.digital.store.features.reservation.data.dto.EditReservationRequestDTO
import br.com.digital.store.features.reservation.data.dto.GenerateReservationsRequestVO
import br.com.digital.store.features.reservation.data.dto.ReservationRequestDTO
import br.com.digital.store.features.reservation.data.dto.ReservationResponseDTO
import br.com.digital.store.features.reservation.data.repository.ReservationRepository
import br.com.digital.store.features.reservation.data.vo.ReservationsResponseVO
import br.com.digital.store.features.reservation.domain.converter.ConverterReservation
import br.com.digital.store.utils.CommonUtils.EMPTY_TEXT
import br.com.digital.store.utils.LocationRoute
import br.com.digital.store.utils.NumbersUtils.NUMBER_ONE
import br.com.digital.store.utils.NumbersUtils.NUMBER_SIXTY
import br.com.digital.store.utils.NumbersUtils.NUMBER_ZERO
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ReservationViewModel(
    private val repository: ReservationRepository,
    private val converter: ConverterReservation
) : ViewModel() {

    private var currentPage: Int = NUMBER_ZERO
    private var sizeDefault: Int = NUMBER_SIXTY
    private var sort: String = ASC

    private val _findAllReservations =
        mutableStateOf<ObserveNetworkStateHandler<ReservationsResponseVO>>(
            ObserveNetworkStateHandler.Loading(l = false)
        )
    val findAllReservations: State<ObserveNetworkStateHandler<ReservationsResponseVO>> =
        _findAllReservations

    private val _findReservationByName =
        mutableStateOf<ObserveNetworkStateHandler<List<ReservationResponseDTO>>>(
            ObserveNetworkStateHandler.Loading(l = false)
        )
    val findReservationByName: State<ObserveNetworkStateHandler<List<ReservationResponseDTO>>> =
        _findReservationByName

    private val _createNewReservation =
        mutableStateOf<ObserveNetworkStateHandler<Unit>>(ObserveNetworkStateHandler.Loading(l = false))
    val createNewReservation: State<ObserveNetworkStateHandler<Unit>> = _createNewReservation

    private val _generateReservations =
        mutableStateOf<ObserveNetworkStateHandler<Unit>>(ObserveNetworkStateHandler.Loading(l = false))
    val generateReservations: State<ObserveNetworkStateHandler<Unit>> = _generateReservations

    private val _editReservation =
        mutableStateOf<ObserveNetworkStateHandler<Unit>>(ObserveNetworkStateHandler.Loading(l = false))
    val editReservation: State<ObserveNetworkStateHandler<Unit>> = _editReservation

    private val _deleteReservation =
        mutableStateOf<ObserveNetworkStateHandler<Unit>>(ObserveNetworkStateHandler.Loading(l = false))
    val deleteReservation: State<ObserveNetworkStateHandler<Unit>> = _deleteReservation

    fun findAllReservations(
        name: String = EMPTY_TEXT,
        sort: String = this.sort,
        size: Int = this.sizeDefault,
        route: LocationRoute = LocationRoute.SEARCH
    ) {
        when (route) {
            LocationRoute.SEARCH, LocationRoute.SORT, LocationRoute.RELOAD -> {}
            LocationRoute.FILTER -> {
                this.currentPage = NUMBER_ZERO
            }
        }
        viewModelScope.launch {
            sizeDefault = size
            repository.findAllReservations(
                name = name,
                page = currentPage,
                size = sizeDefault,
                sort = sort
            )
                .onStart {
                    _findAllReservations.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    it.result?.let { response ->
                        _findAllReservations.value = ObserveNetworkStateHandler.Success(
                            s = converter.converterContentDTOToVO(content = response)
                        )
                    }
                }
        }
    }

    fun loadNextPage() {
        val lastPage = _findAllReservations.value.result?.totalPages ?: 0
        if (currentPage < lastPage - NUMBER_ONE) {
            currentPage++
            findAllReservations()
        }
    }

    fun reloadPreviousPage() {
        if (currentPage > NUMBER_ZERO) {
            currentPage--
            findAllReservations()
        }
    }

    fun findReservationByName(name: String) {
        viewModelScope.launch {
            repository.finReservationByName(name = name)
                .onStart {
                    _findReservationByName.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    _findReservationByName.value = it
                }
        }
    }

    fun createReservation(reservation: String) {
        viewModelScope.launch {
            repository.createNewReservation(reservation = ReservationRequestDTO(name = reservation))
                .onStart {
                    _createNewReservation.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    _createNewReservation.value = it
                }
        }
    }

    fun generateReservations(body: GenerateReservationsRequestVO) {
        viewModelScope.launch {
            repository.generateReservations(body = body)
                .onStart {
                    _generateReservations.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    _generateReservations.value = it
                }
        }
    }

    fun editReservation(reservation: EditReservationRequestDTO) {
        viewModelScope.launch {
            repository.editReservation(reservation = reservation)
                .onStart {
                    _editReservation.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    _editReservation.value = it
                }
        }
    }

    fun deleteReservation(id: Long) {
        viewModelScope.launch {
            repository.deleteReservation(id = id)
                .onStart {
                    _deleteReservation.value = ObserveNetworkStateHandler.Loading(l = true)
                }
                .collect {
                    _deleteReservation.value = it
                }
        }
    }

    fun resetReservation(reset: ResetReservation) {
        when (reset) {
            ResetReservation.FIND_RESERVATION_BY_NAME -> {
                _findReservationByName.value = ObserveNetworkStateHandler.Loading(l = false)
            }

            ResetReservation.CREATE_RESERVATION -> {
                _createNewReservation.value = ObserveNetworkStateHandler.Loading(l = false)
                findAllReservations()
            }

            ResetReservation.GENERATE_RESERVATIONS -> {
                _generateReservations.value = ObserveNetworkStateHandler.Loading(l = false)
                findAllReservations()
            }
        }
    }
}

enum class ResetReservation {
    FIND_RESERVATION_BY_NAME,
    CREATE_RESERVATION,
    GENERATE_RESERVATIONS
}
