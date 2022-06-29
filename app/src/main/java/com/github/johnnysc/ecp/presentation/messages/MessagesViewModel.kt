package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack

class MessagesViewModel(
    canGoBackCallback: CanGoBack.Callback,
    dispatchers: Dispatchers,
    messageCommunication: MessageCommunication
) : BackPress.ViewModel<MessagesUI>(canGoBackCallback, messageCommunication, dispatchers) {

    private var canGoBack = true

    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    override fun updateCallbacks() {
        canGoBackCallback.updateCallback(canGoBackCallbackInner)

    }


}