package com.qverkk.rentafriend.controllers.user

import javax.persistence.*

@Entity
@Table(name = "Users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "userId", unique = true, nullable = false)
        val userId: Int,
        @Column(name = "firstName")
        val firstName: String,
        @Column(name = "lastName")
        val lastName: String,
        @Column(name = "birthDate")
        val birthDate: String,
        @Column(name = "username")
        val username: String,
        @Column(name = "password")
        val password: String) {

    fun toDTO(): UserDTO = UserDTO(userId, firstName, lastName, birthDate, username, password)
}

fun fromUserDTO(dto: UserDTO): User = User(dto.userId, dto.firstName, dto.lastName, dto.birthDate, dto.username, dto.password)
