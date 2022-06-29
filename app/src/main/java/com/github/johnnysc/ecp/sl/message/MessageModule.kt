package com.github.johnnysc.ecp.sl.message

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.ecp.presentation.messages.MessageCommunication
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel

class MessageModule(
    private val canGoBack: CanGoBack.Callback,
    private val dispatchers: Dispatchers,
    private val messageCommunication: MessageCommunication
) : Module<MessagesViewModel> {

    override fun viewModel() = MessagesViewModel(canGoBack, dispatchers, messageCommunication)
}