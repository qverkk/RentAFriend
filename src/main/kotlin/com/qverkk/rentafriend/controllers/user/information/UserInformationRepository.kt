package com.qverkk.rentafriend.controllers.user.information

import org.springframework.data.repository.CrudRepository

interface UserInformationRepository : CrudRepository<UserInformation, Int> {
    fun findByUserId(userId: Int): UserInformationDTO?
    fun findByInformationId(informationId: Int): UserInformationDTO?
}
