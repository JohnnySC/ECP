package com.github.johnnysc.ecp.sl.message

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.ecp.presentation.messages.MessageCommunication
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel

class MessageDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == MessagesViewModel::class.java) {
            val messageCommunication = MessageCommunication.Base()
            MessageModule(
                coreModule.provideCanGoBack(),
                coreModule.dispatchers(),
                messageCommunication
            )
        } else {
            dependencyContainer.module(clazz)
        }
}
