package com.qverkk.rentafriend.controllers.user.messages

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class ChatMessageController {

    @Autowired
    private lateinit var service: JpaChatMessageService

    @GetMapping(
            value = ["/all"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            headers = ["chatId"]
    )
    fun allForChatId(@RequestHeader("chatId") chatId: String): List<ChatMessageDTO> {
        return service.getAllByChatId(chatId)
    }
}
