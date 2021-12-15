package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.ViewModel
import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.ui.core.MainModule
import com.github.johnnysc.ecp.ui.core.MainViewModel

interface DependencyContainer {
    fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T>

    class Error : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T> {
            throw IllegalStateException("no module found for $clazz")
        }
    }

    class Main(private val dependencyContainer: DependencyContainer) : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<T> =
            when (clazz) {
                MainViewModel::class.java -> MainModule(CoreModule()).viewModel() as BaseModule<T>
                else -> dependencyContainer.module(clazz)
            }
    }
}