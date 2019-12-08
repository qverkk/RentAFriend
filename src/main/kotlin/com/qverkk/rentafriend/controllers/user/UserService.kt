package com.qverkk.rentafriend.controllers.user

interface UserService {

    fun getAllUsers(): List<UserDTO>

    fun getAllSecuredUsers(): List<SecuredUser>

    fun findUserByUsername(username: String): UserDTO?

    fun findByCountry(country: String): List<SecuredUser>

    fun findByCityName(city: String): List<SecuredUser>

    fun getUser(userId: Int): UserDTO?

    fun addUser(user: UserDTO): UserDTO?

    fun deleteUser(userId: Int): UserDTO?

    fun updateUser(user: UserDTO): UserDTO?
}
