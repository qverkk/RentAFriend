package com.qverkk.rentafriend.controllers.user

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody

@Service("User service")
class JpaUserService(val userRepo: UserRepository) : UserService {
    override fun getAllUsers(): List<UserDTO> {
        return userRepo.findAllByUserIdAfter(-1)
    }

    override fun findUserByUsername(username: String): UserDTO? {
        return userRepo.findByUsername(username)
    }

    override fun getUser(userId: Int): UserDTO? {
        return userRepo.findByUserId(userId)
    }

    @ResponseBody
    override fun addUser(user: UserDTO): UserDTO? {
        val userFromDb = userRepo.findByUsername(user.username)
        if (userFromDb != null) {
            return userFromDb
        }

        userRepo.save(fromUserDTO(user))
        return userRepo.findByUsername(user.username)
    }

    override fun deleteUser(userId: Int): UserDTO? {
        val user = userRepo.findByUserId(userId)
        if (user == null) {
            return null
        }
        userRepo.deleteById(userId)
        return user
    }

    override fun updateUser(user: UserDTO): UserDTO? {
        val userDb = userRepo.findByUserId(user.userId) ?: return null

        userDb.birthDate = user.birthDate
        userDb.firstName = user.firstName
        userDb.lastName = user.lastName
        userDb.password = user.password


        userRepo.save(fromUserDTO(userDb))
        return userDb
    }
}
