package com.github.johnnysc.ecp.sl.message

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.ecp.presentation.messages.MessagesCommunication
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain

class MessagesModule(

) : Module<MessagesViewModel> {

    override fun viewModel(): MessagesViewModel {

        return MessagesViewModel(

        )
    }
}