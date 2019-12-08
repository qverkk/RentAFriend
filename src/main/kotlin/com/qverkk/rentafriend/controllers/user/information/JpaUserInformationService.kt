package com.qverkk.rentafriend.controllers.user.information

import com.qverkk.rentafriend.controllers.user.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service("User information service")
class JpaUserInformationService(val repository: UserInformationRepository) : InformationService {
    override fun getInformationForUser(user: UserDTO): UserInformationDTO? {
        return repository.findByUserId(user.userId)
    }

    override fun addInformation(information: UserInformationDTO): UserInformationDTO? {
        if (repository.findByUserId(information.userId) != null) {
            return null
        }

        repository.save(fromUserInformationDTO(information))
        val informationDb = repository.findByUserId(information.userId)
        if (informationDb != null) {
            return informationDb
        }
        return null
    }

    override fun deleteInformation(information: UserInformationDTO): ResponseEntity<Any> {
        val databaseInformation = repository.findByInformationId(information.informationId)
                ?: return ResponseEntity("Information doesn't exists", HttpStatus.CONFLICT)

        repository.delete(fromUserInformationDTO(databaseInformation))
        return ResponseEntity("Information has been deleted", HttpStatus.OK)
    }

    override fun updateInformation(information: UserInformationDTO): ResponseEntity<Any> {
        val databaseInformation = repository.findByInformationId(information.informationId)
                ?: return ResponseEntity("Information doesn't exist", HttpStatus.CONFLICT)

        databaseInformation.cityName = information.cityName
        databaseInformation.country = information.country
        databaseInformation.description = information.description
        databaseInformation.price = information.price
        repository.save(fromUserInformationDTO(databaseInformation))
        return ResponseEntity("Information has been updated", HttpStatus.OK)
    }

}
