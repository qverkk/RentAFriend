package com.qverkk.rentafriend.controllers.user.orders

import javax.persistence.*

@Entity
@Table(name = "UserOrders")
data class UserOrders(
        @Id
        @Column(name = "orderId")
        val orderId: String,
        @Column(name = "userRentingId")
        val userRentingId: Int,
        @Column(name = "userRentedId")
        val userRentedId: Int,
        @Column(name = "chatName")
        val chatName: String
) {
    fun toDTO(): UserOrdersDTO = UserOrdersDTO(orderId, userRentingId, userRentedId, chatName)
}

fun fromUserOrdersDTO(order: UserOrdersDTO): UserOrders = UserOrders(order.orderId, order.userRentingId, order.userRentedId, order.chatName)
