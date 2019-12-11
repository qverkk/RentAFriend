package com.qverkk.rentafriend.controllers.user.pictures

data class UserPictureDTO(
        val pictureId: Int,
        val userId: Int,
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
        if (profilePicture != other.profilePicture) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pictureId
        result = 31 * result + userId
        result = 31 * result + imageBase64.contentHashCode()
        result = 31 * result + profilePicture.hashCode()
        return result
    }
}
