package com.qverkk.rentafriend.controllers.user.pictures

import com.qverkk.rentafriend.controllers.user.JpaUserService
import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pictures")
class UserPicturesController {

    @Autowired
    private lateinit var service: JpaUserPictureService
    @Autowired
    private lateinit var userService: JpaUserService

    @PostMapping(
            value = ["/user/add"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["user"]
    )
    fun insertImageForUser(@RequestHeader("user") userId: Int, @RequestBody picture: String): Boolean {
        val image = UserPictureDTO(
                0,
                userId,
                picture,
                true
        )
        service.updatePicture(image)
        return true
    }

    @PostMapping(
            value = ["/update/pictures"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["user"]
    )
    fun updateUserProfilePicture(@RequestHeader("user") userId: Int, @RequestBody picture: String): Boolean {
        val pictures = service.getAllByUserId(userId)
        var result = false
        if (!service.userContainsPicture(userId, picture)) {
            service.updatePicture(
                    UserPictureDTO(
                            0,
                            userId,
                            picture,
                            true
                    )
            )
        }
        pictures.forEach {
            it.profilePicture = it.imageBase64.contentEquals(picture)
            if (it.profilePicture && !result) {
                result = true
            }
            service.updatePicture(it)
        }
        return result
    }

    @PostMapping(
            value = ["/userprofile"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getUserProfileImage(@RequestBody userId: Int): UserPictureDTO? {
        val picture = service.findByUserIdAndProfilePicture(userId, true)
        return picture
    }

    @GetMapping(
            value = ["/get"],
            headers = ["user"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllByUser(@RequestHeader user: UserDTO): List<UserPictureDTO> {
        return service.getAllByUser(user)
    }

    @DeleteMapping(
            value = ["/delete"],
            headers = ["id"]
    )
    fun deletePicture(@RequestHeader id: Int): ResponseEntity<Any> {
        return service.deletePicture(id)
    }

    @GetMapping(
            value = ["/get"],
            headers = ["id"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getPicture(@RequestHeader id: Int): UserPictureDTO? {
        return service.getPicture(id)
    }

    @DeleteMapping(
            value = ["/delete"],
            headers = ["picture", "user"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteByPictureIdAndUserId(@RequestHeader picture: UserPictureDTO, @RequestHeader user: UserDTO): UserPictureDTO? {
        return service.deleteByPictureIdAndUserId(picture, user)
    }
}
