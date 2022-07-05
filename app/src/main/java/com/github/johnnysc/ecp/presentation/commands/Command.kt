package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface Command<T> : FeatureChain.Check {
    fun handle(useCase: T): MessageUI
}