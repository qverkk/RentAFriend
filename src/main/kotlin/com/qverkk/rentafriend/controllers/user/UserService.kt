package com.qverkk.rentafriend.controllers.user

interface UserService {

    fun getAllUsers(): List<UserDTO>

    fun findUserByUsername(username: String): UserDTO?

    fun getUser(userId: Int): UserDTO?

    fun addUser(user: UserDTO): UserDTO?

    fun deleteUser(userId: Int): UserDTO?

    fun updateUser(user: UserDTO): UserDTO?
}
