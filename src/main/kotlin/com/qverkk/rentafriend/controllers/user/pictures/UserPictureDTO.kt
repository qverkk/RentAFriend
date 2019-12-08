package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.User

data class UserPictureDTO(
        val pictureId: Int,
        val userId: User,
        val imageBase64: String
)
