package com.qverkk.rentafriend.controllers.user.orders

import org.springframework.data.repository.CrudRepository

interface UserOrdersRepository: CrudRepository<UserOrders, String> {
    fun findAllByUserRentedIdOrUserRentingId(userId: Int): List<UserOrdersDTO>
    fun findByUserRentedIdAndUserRentingId(rentedId: Int, rentingId: Int): UserOrdersDTO?
    fun findByUserRentingIdAndUserRentedId(rentingId: Int, rentedId: Int): UserOrdersDTO?
}
