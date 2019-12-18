package com.qverkk.rentafriend.controllers.user.messages

import org.springframework.stereotype.Service

@Service("Chat message service")
class JpaChatMessageService(val repository: ChatMessagesRepository): ChatMessageService {
    override fun getAllByChatId(chatId: String): List<ChatMessageDTO> {
        return repository.findAllByChatId(chatId)
    }

    override fun addMessage(message: ChatMessageDTO) {
        repository.save(fromChatMessageDTO(message))
    }
}
