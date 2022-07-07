package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface HandleUseCase<T> {
    suspend fun handle(useCase: T): MessageUI
}