package com.github.johnnysc.ecp.sl.view_model_chain

import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

interface DependencyContainer {
    fun <T : ViewModelChain> module(clazz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModelChain> module(clazz: Class<T>): Module<*> {
            throw IllegalStateException("no module found for $clazz")
        }
    }
}