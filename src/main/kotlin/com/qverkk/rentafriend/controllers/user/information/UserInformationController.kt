package com.qverkk.rentafriend.controllers.user.information

import com.qverkk.rentafriend.controllers.user.JpaUserService
import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/information")
class UserInformationController(val service: JpaUserInformationService, val userService: JpaUserService) {

    @GetMapping(
            value = ["/foruser"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun get(@RequestBody user: UserDTO): UserInformationDTO? {
        val userDb = userService.findUserByUsername(user.username) ?: return null
        return service.getForUser(userDb)
    }

    @PostMapping(
            value = ["/update"],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody information: UserInformationDTO): ResponseEntity<Any> {
        return service.update(information)
    }

    @GetMapping(
            value = ["/all"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAll(): List<UserInformationDTO> {
        return service.getAll()
    }
}
