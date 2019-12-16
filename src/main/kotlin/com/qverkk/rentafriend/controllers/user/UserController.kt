package com.qverkk.rentafriend.controllers.user

import com.qverkk.rentafriend.controllers.user.information.JpaUserInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private lateinit var service: JpaUserService

    @Autowired
    private lateinit var informationService: JpaUserInformationService

    @GetMapping(
            value = ["/country/{countryName}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllUsersForCountry(@PathVariable(value = "countryName") countryName: String): List<SecuredUser> {
        return service.findByCountry(countryName)
    }

    @GetMapping(
            value = ["/city/{cityName}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllUsersForCity(@PathVariable(value = "cityName") cityName: String): List<SecuredUser> {
        return service.findByCityName(cityName)
    }

    @GetMapping(
            value = ["/allUsers"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllSecuredUsers(): List<SecuredUser> {
        return service.getAllSecuredUsers()
    }

    @PostMapping(
            value = ["/login"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["username", "password"]
    )
    fun loginUser(@RequestHeader("username") username: String, @RequestHeader("password") password: String): ResponseEntity<Any> {
        val user = service.findUserByUsername(username)
                ?: return ResponseEntity("The user doesn't exist", HttpStatus.NOT_ACCEPTABLE)
        if (user.password == password) {
            return ResponseEntity(user, HttpStatus.OK)
        }
        return ResponseEntity("The password doesn't match", HttpStatus.NOT_ACCEPTABLE)
    }

    @PostMapping(
            value = ["/logintest"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["username", "password"]
    )
    fun loginUserTest(@RequestHeader("username") username: String, @RequestHeader("password") password: String): ResponseEntity<Any> {
        val user = service.findUserByUsername(username)
                ?: return ResponseEntity("The user doesn't exist", HttpStatus.NOT_ACCEPTABLE)
        if (user.password == password) {
            val informationForUser = informationService.getForUser(user)
                    ?: return ResponseEntity(user, HttpStatus.OK)

            val response = UserWithInformation(user, informationForUser)
            return ResponseEntity(response, HttpStatus.OK)
        }
        return ResponseEntity("The password doesn't match", HttpStatus.NOT_ACCEPTABLE)
    }

    @GetMapping(
            value = ["/all/information"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllUserInformation(): List<UserWithInformation> {
        val users = service.getAllUsers()
        val result = mutableListOf<UserWithInformation>()
        users.forEach {
            val information = informationService.getForUser(it)
            if (information != null) {
                result.add(UserWithInformation(it, information))
            }
        }
        return result
    }

    @GetMapping(
            value = ["/all/information/country"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["countryName"]
    )
    fun getAllUserInformationFromCountry(@RequestHeader("countryName") countryName: String): List<UserWithInformation> {
        val users = service.getAllUsers()
        val result = mutableListOf<UserWithInformation>()
        users.forEach {
            val information = informationService.getForUser(it)
            if (information != null && information.country == countryName) {
                result.add(UserWithInformation(it, information))
            }
        }
        return result
    }

    @GetMapping(
            value = ["/all"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllUsers(): List<UserDTO> {
        return service.getAllUsers()
    }

    @PostMapping(
            value = ["/add"],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addUser(@RequestBody user: UserDTO): ResponseEntity<Any> {
        val userAdded = service.addUser(user) ?: return ResponseEntity("User couldn't be added", HttpStatus.CONFLICT)

        return ResponseEntity(userAdded, HttpStatus.OK)
    }

    @PostMapping(
            value = ["/register"],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addUserWithInformation(@RequestBody userWithInformation: UserWithInformation): ResponseEntity<Any> {
        val userAdded = service.addUser(userWithInformation.user)
                ?: return ResponseEntity("User couldn't be added", HttpStatus.CONFLICT)

        userWithInformation.information.userId = userAdded.userId
        val informationAdded = informationService.add(userWithInformation.information)
                ?: return ResponseEntity("Information couldn't be added", HttpStatus.CONFLICT)

        val response = UserWithInformation(userAdded, informationAdded)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping(
            value = ["/exists"]
    )
    fun userExists(username: String?): Boolean {
        if (username == null) {
            return false
        }
        return service.findUserByUsername(username) != null
    }
}
