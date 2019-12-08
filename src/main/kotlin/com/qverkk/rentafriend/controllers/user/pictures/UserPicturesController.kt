package com.qverkk.rentafriend.controllers.user.pictures

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
