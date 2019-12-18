package com.qverkk.rentafriend.controllers.user.messages

interface ChatMessageService {
    fun getAllByChatId(chatId: String): List<ChatMessageDTO>
    fun addMessage(message: ChatMessageDTO)
}
