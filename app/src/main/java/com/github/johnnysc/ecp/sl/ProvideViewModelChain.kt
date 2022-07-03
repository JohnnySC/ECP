package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

interface ProvideViewModelChain<T : ViewModelChain> {

    fun viewModelChain(): T
}