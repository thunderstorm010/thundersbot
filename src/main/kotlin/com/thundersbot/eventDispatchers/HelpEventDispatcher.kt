package com.thundersbot.eventDispatchers

import com.thundersbot.lib.ExtendedString
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

class HelpEventDispatcher(var gateway: GatewayDiscordClient) {
    public fun dispatch() {
        val helpmsgs = arrayOf("yardım","help","!help","!yardım","g!help","g!yardım")

        with(ExtendedString()) {
            gateway.eventDispatcher.on(discord4j.core.event.domain.message.MessageCreateEvent::class.java)
                .filter {t -> t.message.content.equalsAnyOf(helpmsgs,true)}
                .filter {t -> t.member.orElse(null) != null && !t.member.get().isBot}
                .subscribe { mce ->

                }
        }

    }
}