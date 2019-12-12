package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.ResponseEntity

interface UserPictureService {
    fun getAllByUserId(userId: Int): List<UserPictureDTO>
    fun getAllByUser(user: UserDTO): List<UserPictureDTO>
    fun deletePicture(id: Int): ResponseEntity<Any>
    fun getPicture(id: Int): UserPictureDTO?
    fun deleteByPictureIdAndUserId(picture: UserPictureDTO, user: UserDTO): UserPictureDTO?
    fun updatePicture(picture: UserPictureDTO)
    fun userContainsPicture(userId: Int, picture: String): Boolean
    fun findByUserIdAndProfilePicture(userId: Int, profilePicture: Boolean): UserPictureDTO?
}
