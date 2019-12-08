package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.UserDTO
import com.qverkk.rentafriend.controllers.user.UserRepository
import com.qverkk.rentafriend.controllers.user.fromUserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service("User picture service")
class JpaUserPictureService(val userPicturesRepository: UserPicturesRepository, val userRepo: UserRepository) : UserPictureService {
    override fun getAllByUser(user: UserDTO): List<UserPictureDTO> {
        if (userRepo.findByUsername(user.username) == null) {
            return emptyList()
        }

        return userPicturesRepository.findAllByUserId(fromUserDTO(user))
    }

    override fun deletePicture(id: Int): ResponseEntity<Any> {
        userPicturesRepository.deleteById(id)
        return ResponseEntity("Picture has been deleted", HttpStatus.OK)
    }

    override fun getPicture(id: Int): UserPictureDTO? {
        return userPicturesRepository.findByPictureId(id)
    }

    override fun deleteByPictureIdAndUserId(picture: UserPictureDTO, user: UserDTO): UserPictureDTO? {
        if (userRepo.findByUsername(user.username) == null) {
            return null
        }

        return userPicturesRepository.deleteByUserIdAndPictureId(fromUserDTO(user), picture.pictureId)
    }
}
