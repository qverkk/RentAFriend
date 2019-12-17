package com.qverkk.rentafriend.controllers.user

data class MessageDTO(
        val userFirstName: String,
        val userLastName: String,
        val chatId: String,
        val fromUser: String,
        val textBody: String
)
