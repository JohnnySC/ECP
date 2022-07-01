package com.github.johnnysc.ecp.sl.message

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel

class MessagesDependencyContainer(
    private val dependencyContainer: DependencyContainer,
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == MessagesViewModel::class.java) {
            MessagesModule()
        } else {
            dependencyContainer.module(clazz)
        }
}
