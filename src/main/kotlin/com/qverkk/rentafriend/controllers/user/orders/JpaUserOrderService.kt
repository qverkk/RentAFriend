package com.qverkk.rentafriend.controllers.user.orders

import com.qverkk.rentafriend.controllers.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service("User orders service")
class JpaUserOrderService(val userOrdersRepository: UserOrdersRepository, val userRepository: UserRepository): UserOrderService {

    override fun getAllOrdersByUser(userId: Int): List<UserOrdersDTO> {
        return userOrdersRepository.findAllByUserRentedIdOrUserRentingId(userId)
    }

    override fun addOrderBetween(rentingUser: Int, rentedUser: Int) {
        if (!orderBetweenUsersExists(rentingUser, rentedUser)) {
            val rentedUserObj = userRepository.findByUserId(rentedUser)
            val rentingUserObj = userRepository.findByUserId(rentingUser)
            val newChatName = "Chat between ${rentedUserObj!!.firstName} and ${rentingUserObj!!.firstName}"
            userOrdersRepository.save(fromUserOrdersDTO(UserOrdersDTO(UUID.randomUUID().toString(), rentingUser, rentedUser, newChatName)))
        }
    }

    private fun orderBetweenUsersExists(renting: Int, rented: Int): Boolean {
        return userOrdersRepository.findByUserRentedIdAndUserRentingId(rented, renting) != null ||
                userOrdersRepository.findByUserRentingIdAndUserRentedId(renting, rented) != null
    }
}
