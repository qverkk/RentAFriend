package com.qverkk.rentafriend.controllers.user

import com.qverkk.rentafriend.controllers.user.information.UserInformationRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody

@Service("User service")
class JpaUserService(val userRepo: UserRepository, val informationRepository: UserInformationRepository) : UserService {
    override fun getAllUsers(): List<UserDTO> {
        return userRepo.findAllByUserIdAfter(-1)
    }

    override fun getAllSecuredUsers(): List<SecuredUser> {
        val users = userRepo.findAllByUserIdAfter(0)
        val result = mutableListOf<SecuredUser>()
        users.forEach {
            val info = informationRepository.findByUserId(it.userId)
            if (info != null) {
                result.add(SecuredUser(
                        it.firstName,
                        it.lastName,
                        it.birthDate,
                        info.description,
                        info.price,
                        info.country,
                        info.cityName
                ))
            }
        }
        return result
    }

    override fun findUserByUsername(username: String): UserDTO? {
        return userRepo.findByUsername(username)
    }

    override fun findByCountry(country: String): List<SecuredUser> {
        val users = userRepo.findAllByUserIdAfter(0)
        val result = mutableListOf<SecuredUser>()
        users.forEach {
            val info = informationRepository.findByUserId(it.userId)
            if (info != null && info.country == country) {
                result.add(SecuredUser(
                        it.firstName,
                        it.lastName,
                        it.birthDate,
                        info.description,
                        info.price,
                        info.country,
                        info.cityName
                ))
            }
        }
        return result
    }

    override fun findByCityName(city: String): List<SecuredUser> {
        val users = userRepo.findAllByUserIdAfter(0)
        val result = mutableListOf<SecuredUser>()
        users.forEach {
            val info = informationRepository.findByUserId(it.userId)
            if (info != null && info.country == city) {
                result.add(SecuredUser(
                        it.firstName,
                        it.lastName,
                        it.birthDate,
                        info.description,
                        info.price,
                        info.country,
                        info.cityName
                ))
            }
        }
        return result
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
