package com.github.johnnysc.ecp.presentation.messages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.coremvvm.core.Dispatchers

class MessagesViewModel(
    private val dispatchers: Dispatchers,
    private val communication: MessagesCommunication.Mutable,
    private val viewModelChain: FeatureChain.Handle
) : ViewModel(), MessagesCommunication.Observe {

    fun handleInput(message: String) {
        communication.map(MessageUI.User("0", message))
        dispatchers.launchBackground(viewModelScope) {
            dispatchers.changeToUI { communication.map(viewModelChain.handle(message)) }
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>) =
        communication.observe(owner, observer)
}