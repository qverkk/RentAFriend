package com.qverkk.rentafriend.controllers.user.orders

data class UserOrdersDTO(
        val orderId: String,
        val userRentingId: Int,
        val userRentedId: Int,
        val chatName: String
)
