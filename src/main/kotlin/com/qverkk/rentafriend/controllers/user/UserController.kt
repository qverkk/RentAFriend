package com.qverkk.rentafriend.controllers.user

import com.qverkk.rentafriend.controllers.user.information.JpaUserInformationService
import net.minidev.json.JSONObject
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    private val serverKey = "AAAA6_hDyBQ:APA91bEZ5SsRZ1ZwsP9K39A1pS2IiVmF2_Z3KcWhAT5Sd9bhefZM_1g1qZ7aBuA_CzdDyvrtAWdkdILHt3IUNGZEegHzh5jQlZWvv6wRoOKluGqB7o3_6YISG5737jnmjEUoZijpNrbr"

    @Autowired
    private lateinit var service: JpaUserService

    @Autowired
    private lateinit var informationService: JpaUserInformationService

    @PostMapping(
            value = ["/send"],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun sendMessage(@RequestBody message: MessageDTO): Boolean {
        val client = OkHttpClient()

        val json = JSONObject()
        json["to"] = "/topics/${message.chatId}"
        val notificationObj = JSONObject()
        notificationObj["title"] = "New message"
        notificationObj["body"] = "Mew message from ${message.userFirstName} ${message.userLastName}"
        json["notification"] = notificationObj
        val text = JSONObject()
        text["fromUser"] = message.fromUser
        text["textBody"] = message.textBody
        json["data"] = text

        val requestBody = okhttp3.RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                json.toString()
        )

        val request = Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .addHeader("content-type", "application/json; UTF-8")
                .addHeader("authorization", "key=$serverKey")
                .addHeader("accept", "application/json")
                .post(requestBody)
                .build()

        val execute = client.newCall(request).execute()
        println(execute.body()?.string())
        return execute.isSuccessful
    }

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
