package com.github.johnnysc.ecp.sl.message

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

class MessagesDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule,
    private val viewModelChain: ViewModelChain,
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>) = if (clazz == MessagesViewModel::class.java) {
        MessagesModule(coreModule, viewModelChain)
    } else {
        dependencyContainer.module(clazz)
    }
}
