package br.com.digital.store.common.order.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddressResponseDTO(
    val id: Int,
    val complement: String,
    val district: String,
    val number: Int,
    val street: String
)
