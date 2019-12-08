package com.qverkk.rentafriend.controllers.user.information

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "UserInformations")
data class UserInformation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "informationId")
        val informationId: Int,
        @Column(name = "userId")
        val userId: Int,
        @Column(name = "description")
        val description: String,
        @Column(name = "price")
        val price: BigDecimal,
        @Column(name = "country")
        val country: String,
        @Column(name = "cityName")
        val cityName: String
) {
    fun toUserInformationDTO(): UserInformationDTO = UserInformationDTO(informationId, userId, description, price, country, cityName)
}

fun fromUserInformationDTO(info: UserInformationDTO): UserInformation = UserInformation(
        info.informationId,
        info.userId,
        info.description,
        info.price,
        info.country,
        info.cityName
)
