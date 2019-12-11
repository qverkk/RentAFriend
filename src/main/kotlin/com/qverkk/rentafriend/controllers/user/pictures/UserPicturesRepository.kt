package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.User
import org.springframework.data.repository.CrudRepository

interface UserPicturesRepository : CrudRepository<UserPicture, Int> {
    fun findAllByUserId(user: Int): List<UserPictureDTO>
    fun deleteByUserIdAndPictureId(user: User, pictureId: Int): UserPictureDTO?
    fun findByPictureId(pictureId: Int): UserPictureDTO?
    fun findByUserIdAndProfilePicture(userId: Int, profilePicture: Boolean): UserPictureDTO?
}
