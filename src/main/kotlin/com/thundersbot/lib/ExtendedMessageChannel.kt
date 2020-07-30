package com.thundersbot.lib

import discord4j.common.util.Snowflake
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.channel.GuildMessageChannel
import discord4j.core.`object`.entity.channel.MessageChannel
import reactor.core.publisher.Flux


open class ExtendedMessageChannel {
    open fun MessageChannel.getMessagesBefore(messageId: Snowflake?, count: Long): Flux<Message>? {
        return getMessagesBefore(messageId).take(count)
    }

    open fun MessageChannel.deleteMessagesBefore(message: Message) {
        (message.channel.block() as GuildMessageChannel).bulkDelete((message.channel.block()?.getMessagesBefore(message.id)?.map(Message::getId))).subscribe()
    }

    open fun MessageChannel.deleteMessagesBefore(message: Message, count: Long) {
        (message.channel.block() as GuildMessageChannel).bulkDelete(message.channel.block()!!.getMessagesBefore(message.id,count)?.map(Message::getId)).subscribe()
    }
}

