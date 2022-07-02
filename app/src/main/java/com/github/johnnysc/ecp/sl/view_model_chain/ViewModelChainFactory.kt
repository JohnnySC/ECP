package com.github.johnnysc.ecp.sl.view_model_chain

import com.github.johnnysc.ecp.presentation.messages.ViewModelChain

interface ViewModelChainFactory {
    fun <T : ViewModelChain> create(chainClass: Class<T>): T

    class Base(private val dependencyContainer: DependencyContainer) : ViewModelChainFactory {

        override fun <T : ViewModelChain> create(chainClass: Class<T>): T =
            dependencyContainer.module(chainClass).viewModelChain() as T
    }
}