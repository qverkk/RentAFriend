package com.qverkk.rentafriend.controllers.user.pictures

data class UserPictureDTO(
        val pictureId: Int,
        val userId: Int,
        val imageBase64: String,
        var profilePicture: Boolean
)
