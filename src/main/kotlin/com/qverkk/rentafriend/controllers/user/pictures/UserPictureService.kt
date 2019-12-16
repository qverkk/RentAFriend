package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.ResponseEntity

interface UserPictureService {
    fun getAllByUserId(userId: Int): List<UserPictureDTO>
    fun getAllByUser(user: UserDTO): List<UserPictureDTO>
    fun deleteById(id: Int): ResponseEntity<Any>
    fun getById(id: Int): UserPictureDTO?
    fun deleteByPictureIdAndUserId(picture: UserPictureDTO, user: UserDTO): UserPictureDTO?
    fun update(picture: UserPictureDTO)
    fun userContainsPicture(userId: Int, picture: String): Boolean
    fun findByUserIdAndProfilePicture(userId: Int, profilePicture: Boolean): UserPictureDTO?
}
