package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R

interface FeatureChain {
    interface Check<T> : FeatureChain {
        suspend fun canHandle(message: String): T
    }

    interface Handle : FeatureChain {
        suspend fun handle(): MessageUI
    }

    interface CheckAndHandle<T> : Check<T>, Handle

    class UnknownMessageChain(private val manageResources: ManageResources) : CheckAndHandle<Boolean> {

        override suspend fun canHandle(message: String) = true

        override suspend fun handle() = MessageUI.AiError(
            manageResources.string(R.string.i_dont_understand)
        )
    }

    class Empty : Check<MessageUI> {

        override suspend fun canHandle(message: String) = MessageUI.Empty()
    }

}