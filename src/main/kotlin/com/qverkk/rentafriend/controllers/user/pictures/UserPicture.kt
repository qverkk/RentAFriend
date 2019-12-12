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
        @Column(name = "imageBase64", nullable = false, columnDefinition = "LONGTEXT")
        val imageBase64: String,
        @Column(name = "profilePicture", nullable = false)
        val profilePicture: Boolean
) {
    fun toDTO(): UserPictureDTO = UserPictureDTO(pictureId, userId, imageBase64, profilePicture)
}

fun fromUserPictureDTO(picture: UserPictureDTO): UserPicture = UserPicture(picture.pictureId, picture.userId, picture.imageBase64, picture.profilePicture)
