package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.User
import javax.persistence.*

@Entity
@Table(name = "UserPictures")
data class UserPicture(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pictureId")
        val pictureId: Int,
        @ManyToOne
        @JoinColumn(name = "userId", nullable = false)
        val userId: User,
        @Column(name = "imageBase64", nullable = false)
        val imageBase64: String
) {
    fun toDTO(): UserPictureDTO = UserPictureDTO(pictureId, userId, imageBase64)
}

fun fromUserPictureDTO(picture: UserPictureDTO): UserPicture = UserPicture(picture.pictureId, picture.userId, picture.imageBase64)
