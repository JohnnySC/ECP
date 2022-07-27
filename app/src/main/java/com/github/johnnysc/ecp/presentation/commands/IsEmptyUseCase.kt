package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface IsEmptyUseCase<T> : IsEmpty, HandleUseCase<T> {

    class Empty<T> : IsEmptyUseCase<T> {
        override suspend fun handle(useCase: T): MessageUI {
            return MessageUI.Empty()
        }

        override fun isEmpty() = true
    }
}