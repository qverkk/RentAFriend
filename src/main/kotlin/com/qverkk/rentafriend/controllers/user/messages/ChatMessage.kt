package com.qverkk.rentafriend.controllers.user.messages

import javax.persistence.*

@Entity
@Table(name = "ChatMessages")
data class ChatMessage(
        @Id
        @Column(name = "messageId")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val messageId: Int,
        @Column(name = "userFirstName")
        val userFirstName: String,
        @Column(name = "userLastName")
        val userLastName: String,
        @Column(name = "chatId")
        val chatId: String,
        @Column(name = "fromUser")
        val fromUser: Int,
        @Column(name = "toUser")
        val toUser: Int,
        @Column(name = "textBody")
        val textBody: String
)

fun fromChatMessageDTO(message: ChatMessageDTO): ChatMessage = ChatMessage(message.messageId, message.userFirstName, message.userLastName, message.chatId, message.fromUser, message.toUser, message.textBody)
