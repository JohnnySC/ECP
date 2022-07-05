package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface Command<T> : FeatureChain.Check {
    suspend fun handle(useCase: T): MessageUI

    class Empty : Command<Unit> {
        override suspend fun handle(useCase: Unit): MessageUI = MessageUI.Empty()

        override fun canHandle(message: String): Boolean = true
    }
}