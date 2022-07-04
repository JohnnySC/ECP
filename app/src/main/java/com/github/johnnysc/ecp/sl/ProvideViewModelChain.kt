package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

interface ProvideViewModelChain<T : ViewModelChain> {

    fun viewModelChain(): T

    class Base(private vararg val provideViewModelChain: ProvideViewModelChain<out ViewModelChain>) :
        ProvideViewModelChain<ViewModelChain> {
        override fun viewModelChain(): ViewModelChain {
            val listOfViewModelChain = provideViewModelChain.map { provideViewModelChain ->
                provideViewModelChain.viewModelChain()
            }

            val finalChain = listOfViewModelChain.first()

            listOfViewModelChain.forEachIndexed { index, viewModelChain ->
                if (index < listOfViewModelChain.lastIndex)
                    viewModelChain.setNextFeatureChain(listOfViewModelChain[index + 1])

            }
            return finalChain
        }
    }
}