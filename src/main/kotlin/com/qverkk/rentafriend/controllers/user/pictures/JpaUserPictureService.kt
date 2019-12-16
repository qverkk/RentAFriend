package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.UserDTO
import com.qverkk.rentafriend.controllers.user.UserRepository
import com.qverkk.rentafriend.controllers.user.fromUserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service("User picture service")
class JpaUserPictureService(val userPicturesRepository: UserPicturesRepository, val userRepo: UserRepository) : UserPictureService {
    override fun getAllByUserId(userId: Int): List<UserPictureDTO> {
        if (userRepo.findByUserId(userId) == null) {
            return emptyList()
        }

        return userPicturesRepository.findAllByUserId(userId)
    }

    override fun getAllByUser(user: UserDTO): List<UserPictureDTO> {
        if (userRepo.findByUsername(user.username) == null) {
            return emptyList()
        }

        return userPicturesRepository.findAllByUserId(user.userId)
    }

    override fun deleteById(id: Int): ResponseEntity<Any> {
        userPicturesRepository.deleteById(id)
        return ResponseEntity("Picture has been deleted", HttpStatus.OK)
    }

    override fun getById(id: Int): UserPictureDTO? {
        return userPicturesRepository.findByPictureId(id)
    }

    override fun deleteByPictureIdAndUserId(picture: UserPictureDTO, user: UserDTO): UserPictureDTO? {
        if (userRepo.findByUsername(user.username) == null) {
            return null
        }

        return userPicturesRepository.deleteByUserIdAndPictureId(fromUserDTO(user), picture.pictureId)
    }

    override fun update(picture: UserPictureDTO) {
        userPicturesRepository.save(fromUserPictureDTO(picture))
    }

    override fun userContainsPicture(userId: Int, picture: String): Boolean {
        val findAllByUserId = userPicturesRepository.findAllByUserId(userId)
        return findAllByUserId.stream().anyMatch { it.imageBase64.contentEquals(picture) }
    }

    override fun findByUserIdAndProfilePicture(userId: Int, profilePicture: Boolean): UserPictureDTO? {
        return userPicturesRepository.findFirstByUserIdAndProfilePicture(userId, profilePicture)
    }
}
