package com.qverkk.rentafriend.controllers.user

import java.math.BigDecimal

class SecuredUser(
        val firstName: String,
        val lastName: String,
        val birthDate: String,
        val description: String,
        val price: BigDecimal,
        val country: String,
        val cityName: String
)
