package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

/**
 * @param T Interactor
 */
interface Command<T> : FeatureChain.Check, HandleUseCase<T> {

    class Empty<T> : Command<T> {
        override suspend fun handle(useCase: T): MessageUI = MessageUI.Empty()

        override fun canHandle(message: String): Boolean = false
    }
}