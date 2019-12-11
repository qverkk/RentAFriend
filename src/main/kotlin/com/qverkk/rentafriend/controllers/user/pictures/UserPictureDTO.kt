package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.User

data class UserPictureDTO(
        val pictureId: Int,
        val userId: User,
        val imageBase64: ByteArray,
        var profilePicture: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPictureDTO

        if (pictureId != other.pictureId) return false
        if (userId != other.userId) return false
        if (!imageBase64.contentEquals(other.imageBase64)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pictureId
        result = 31 * result + userId.hashCode()
        result = 31 * result + imageBase64.contentHashCode()
        return result
    }
}
