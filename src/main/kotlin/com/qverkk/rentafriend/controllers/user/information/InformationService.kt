package com.qverkk.rentafriend.controllers.user.information

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.ResponseEntity

interface InformationService {
    fun getForUser(user: UserDTO): UserInformationDTO?
    fun add(information: UserInformationDTO): UserInformationDTO?
    fun delete(information: UserInformationDTO): ResponseEntity<Any>
    fun update(information: UserInformationDTO): ResponseEntity<Any>
    fun getAll(): List<UserInformationDTO>
}
