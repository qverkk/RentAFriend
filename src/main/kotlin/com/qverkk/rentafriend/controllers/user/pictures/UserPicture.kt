package com.qverkk.rentafriend.controllers.user.pictures

import javax.persistence.*

@Entity
@Table(name = "UserPictures")
data class UserPicture(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pictureId")
        val pictureId: Int,
        @Column(name = "userId", nullable = false)
        val userId: Int,
        @Column(name = "imageBase64", nullable = false, columnDefinition = "MEDIUMTEXT")
        val imageBase64: ByteArray,
        @Column(name = "profilePicture", nullable = false)
        val profilePicture: Boolean
) {
    fun toDTO(): UserPictureDTO = UserPictureDTO(pictureId, userId, imageBase64, profilePicture)
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as UserPicture

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

fun fromUserPictureDTO(picture: UserPictureDTO): UserPicture = UserPicture(picture.pictureId, picture.userId, picture.imageBase64, picture.profilePicture)
