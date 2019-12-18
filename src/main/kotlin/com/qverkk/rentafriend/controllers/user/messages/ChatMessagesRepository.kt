package com.qverkk.rentafriend.controllers.user.messages

import org.springframework.data.repository.CrudRepository

interface ChatMessagesRepository: CrudRepository<ChatMessage, Int> {
    fun findAllByChatId(chatId: String): List<ChatMessageDTO>
}
