package com.qverkk.rentafriend.controllers.user.orders

interface UserOrderService {
    fun getAllByUser(userId: Int): List<UserOrdersDTO>
    fun addOrderBetween(rentingUser: Int, rentedUser: Int): Boolean
    fun isUserRented(rentingUser: Int, rentedUser: Int): Boolean
}
