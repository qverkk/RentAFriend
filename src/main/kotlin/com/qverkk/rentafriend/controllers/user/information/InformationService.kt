package com.qverkk.rentafriend.controllers.user.information

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.ResponseEntity

interface InformationService {
    fun getInformationForUser(user: UserDTO): UserInformationDTO?
    fun addInformation(information: UserInformationDTO): UserInformationDTO?
    fun deleteInformation(information: UserInformationDTO): ResponseEntity<Any>
    fun updateInformation(information: UserInformationDTO): ResponseEntity<Any>
    fun allInformation(): List<UserInformationDTO>
}
