package com.github.johnnysc.ecp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.core.ProvideViewModel
import com.github.johnnysc.ecp.data.core.DependencyContainer
import com.github.johnnysc.ecp.ui.core.ViewModelsFactory

class App : Application(), ProvideViewModel {

    private val coreModule = CoreModule()

    private val factory by lazy {
        ViewModelsFactory(
            DependencyContainer.Base(
                DependencyContainer.Main(
                    DependencyContainer.Error(),
                    coreModule
                ), coreModule
            )
        )
    }

    override fun <T : ViewModel> provideViewModel(
        clazz: Class<T>,
        viewModelStoreOwner: ViewModelStoreOwner
    ): T {
        TODO("Not yet implemented")
    }
}