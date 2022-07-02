package com.github.johnnysc.ecp.sl.view_model_chain

import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

interface ProvideViewModelChain<T : ViewModelChain> {
    fun viewModelChain(): T

}