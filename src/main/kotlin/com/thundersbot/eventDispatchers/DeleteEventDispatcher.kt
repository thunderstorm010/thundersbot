package com.thundersbot.eventDispatchers


import com.thundersbot.lib.ExtendedMessageChannel
import com.thundersbot.lib.ExtendedString
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.rest.util.Permission


class DeleteEventDispatcher(var gateway: GatewayDiscordClient) {


    fun dispatch() {
        with(ExtendedMessageChannel()) {
            with(ExtendedString()) {
                gateway.eventDispatcher.on(MessageCreateEvent::class.java)
                    .filter { t -> t.member.orElse(null) != null && !t.member.get().isBot }
                    .filter { t -> t.message.content.startsWithAnyOf(arrayOf("g!delete", "g!sil"), ignoreCase = true) }
                    .filter { t -> t.message.content.split(" ").getOrNull(1)?.toLongOrNull() != null }
                    .filter { t -> t.member.get().basePermissions.block()!!.contains(Permission.MANAGE_MESSAGES) }
                    .map(MessageCreateEvent::getMessage)
                    .subscribe { mce ->

                        mce.channel.block()?.deleteMessagesBefore(mce, mce.content.split(" ")[1].toLong())
                        mce.delete().subscribe()
                        if (mce.content.startsWith("sil")) {
                            mce.channel.block()
                                ?.createMessage("**${mce.content.split(" ")[1]}** mesaj uzaya fırlatıldı, ${mce.author.get().mention}")
                                ?.block().let {
                                Thread {
                                    Thread.sleep(1000)
                                    it?.delete()?.subscribe()
                                }.start()
                            }
                        } else {
                            mce.channel.block()
                                ?.createMessage("**${mce.content.split(" ")[1]}** messages have been thrown into the space, ${mce.author.get().mention}")
                                ?.block().let {
                                Thread {
                                    Thread.sleep(1000)
                                    it?.delete()?.subscribe()
                                }.start()
                            }
                        }


                    }
            }
        }

    }

}