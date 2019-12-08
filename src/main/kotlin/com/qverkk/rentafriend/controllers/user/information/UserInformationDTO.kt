package com.qverkk.rentafriend.controllers.user.information

import java.math.BigDecimal

data class UserInformationDTO(
        val informationId: Int,
        var userId: Int,
        var description: String,
        var price: BigDecimal,
        var country: String,
        var cityName: String
)
