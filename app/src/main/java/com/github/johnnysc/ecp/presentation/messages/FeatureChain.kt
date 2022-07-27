package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R

interface FeatureChain {
    interface Check : FeatureChain {
        fun canHandle(message: String): Boolean
    }

    interface Handle : FeatureChain {
        suspend fun handle(message: String): MessageUI
    }

    interface Execute:FeatureChain
    {
        suspend fun execute():MessageUI
    }

    interface CheckAndExecute:Check,Execute

    interface CheckAndHandle : Check, Handle

    class UnknownMessageChain(private val manageResources: ManageResources) : CheckAndExecute {

        override fun canHandle(message: String) = true

        override suspend fun execute() = MessageUI.AiError(
            manageResources.string(R.string.i_dont_understand)
        )
    }

    class Empty : Handle {
        override suspend fun handle(message: String) = MessageUI.Empty()
    }

}