package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.ResponseEntity

interface UserPictureService {
    fun getAllByUser(user: UserDTO): List<UserPictureDTO>
    fun deletePicture(id: Int): ResponseEntity<Any>
    fun getPicture(id: Int): UserPictureDTO?
    fun deleteByPictureIdAndUserId(picture: UserPictureDTO, user: UserDTO): UserPictureDTO?
}
