package com.thundersbot.eventDispatchers

import discord4j.core.GatewayDiscordClient

object DispatchAll {
    fun dispatchAll(gateway: GatewayDiscordClient) {
        DeleteEventDispatcher(gateway).dispatch()
    }
}