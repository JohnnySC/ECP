package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.ViewModel
import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.sl.DictionaryModule
import com.github.johnnysc.ecp.ui.core.MainModule
import com.github.johnnysc.ecp.ui.core.MainViewModel
import com.github.johnnysc.ecp.ui.dictionary.DictionaryViewModel

interface DependencyContainer {
    fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*>

    class Error : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*> {
            throw IllegalStateException("no module found for $clazz")
        }
    }

    class Main(
        private val dependencyContainer: DependencyContainer,
        private val coreModule: CoreModule
    ) : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*> =
            when (clazz) {
                MainViewModel::class.java -> MainModule(coreModule)
                else -> dependencyContainer.module(clazz)
            }
    }

    class Base(
        private val dependencyContainer: DependencyContainer,
        private val coreModule: CoreModule
    ) : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*> =
            when (clazz) {
                DictionaryViewModel::class.java -> DictionaryModule(coreModule)
                else -> dependencyContainer.module(clazz)
            }
    }
}