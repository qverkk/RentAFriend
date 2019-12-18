package com.qverkk.rentafriend.controllers.user.messages

data class ChatMessageDTO(
        val messageId: Int,
        val userFirstName: String,
        val userLastName: String,
        val chatId: String,
        val fromUser: Int,
        val toUser: Int,
        val textBody: String
)
