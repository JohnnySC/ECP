package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.ViewModel

interface DependencyContainer {
    fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T>

    class Error : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T> {
            throw IllegalStateException("no module found for $clazz")
        }
    }
}