package com.qverkk.rentafriend.controllers.user

import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<User, Int> {
    fun findAllByUserIdAfter(id: Int): List<UserDTO>
    fun findByUserId(id: Int): UserDTO?
    fun findByUsername(username: String): UserDTO?
}
