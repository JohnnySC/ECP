package com.github.johnnysc.ecp.sl.message

import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.sl.Module
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.ecp.presentation.messages.MessagesCommunication
import com.github.johnnysc.ecp.presentation.messages.MessagesViewModel
import com.github.johnnysc.ecp.presentation.messages.ViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain

class MessagesModule(
    private val providesViewModelChain: List<ProvideViewModelChain<out ViewModelChain>>,
    private val dispatchers: Dispatchers,
    private val messagesCommunication: MessagesCommunication.Mutable
) : Module<MessagesViewModel> {

    override fun viewModel(): MessagesViewModel {
        val listOfViewModelChain = providesViewModelChain.map { provideViewModelChain ->
            provideViewModelChain.viewModelChain()
        }

        val finalChain = listOfViewModelChain.first()

        listOfViewModelChain.forEachIndexed { index, viewModelChain ->
            if (index < listOfViewModelChain.lastIndex)
                listOfViewModelChain[index].setNextFeatureChain(listOfViewModelChain[index + 1])
        }
        return MessagesViewModel(
            dispatchers = dispatchers,
            communication = messagesCommunication,
            viewModelChain = finalChain
        )
    }
}