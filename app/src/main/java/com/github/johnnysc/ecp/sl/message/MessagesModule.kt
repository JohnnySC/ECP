package com.github.johnnysc.ecp.sl.message

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.ecp.presentation.messages.MessagesCommunication
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

class MessagesModule(
    private val coreModule: CoreModule,
    private val viewModelChain: ViewModelChain
) : Module<MessagesViewModel> {

    override fun viewModel() =
        MessagesViewModel(coreModule.dispatchers(), MessagesCommunication.Base(), viewModelChain)
}