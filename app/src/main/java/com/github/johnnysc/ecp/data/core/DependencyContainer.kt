package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.ViewModel
import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.ui.core.MainModule
import com.github.johnnysc.ecp.ui.core.MainViewModel

interface DependencyContainer {
    fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T>

    class Main(private val coreModule: CoreModule) : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T> =
            when (clazz) {
                MainViewModel::class.java -> MainModule(coreModule = coreModule) as BaseModule<T>
                else -> throw IllegalStateException("no module found for $clazz")
            }
    }

    class Error : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T> {
            throw IllegalStateException("no module found for $clazz")
        }
    }
}