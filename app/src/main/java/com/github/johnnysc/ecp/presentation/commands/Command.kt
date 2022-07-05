package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface Command : FeatureChain.Check {
    fun <T> handle(useCase: T): MessageUI

    class Empty : Command {
        override fun <T> handle(useCase: T): MessageUI = MessageUI.Empty()

        override fun canHandle(message: String): Boolean = true
    }
}