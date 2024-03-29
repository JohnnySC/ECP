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

    interface CheckAndHandle : Check, Handle

    class UnknownMessageChain(private val manageResources: ManageResources) : CheckAndHandle {
        private val errorMessageId = "-1"

        override fun canHandle(message: String) = true

        override suspend fun handle(message: String) = MessageUI.AiError(
            manageResources.string(R.string.i_dont_understand)
        )
    }

    class Empty : Handle {
        override suspend fun handle(message: String) = MessageUI.Empty()
    }

}