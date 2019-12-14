package com.qverkk.rentafriend.controllers.user.orders

interface UserOrderService {
    fun getAllOrdersByUser(userId: Int): List<UserOrdersDTO>
    fun addOrderBetween(rentingUser: Int, rentedUser: Int): Boolean
}
