package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers

class MessagesViewModel(
    private val dispatchers: Dispatchers,
    private val communication: MessagesCommunication.Mutable,
    private val viewModelChain: FeatureChain.Check<MessageUI>
) : ViewModel(), MessagesCommunication.Observe, HandleInput {

    override fun handleInput(message: String) {
        communication.map(MessageUI.User(message))
        dispatchers.launchBackground(viewModelScope) {
            val messageUI = viewModelChain.canHandle(message)
            dispatchers.changeToUI { communication.map(messageUI) }
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) =
        communication.observe(owner, observer)
}