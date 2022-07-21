package com.github.johnnysc.ecp.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.coremvvm.sl.ViewModelsFactory

import com.github.johnnysc.ecp.sl.main.MainDependencyContainer
import com.github.johnnysc.ecp.sl.message.MessagesDependencyContainer
import com.github.johnnysc.ecp.sl.weather.*

class MainApplication : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        val coreModule = CoreModule.Base(this)
        val main = MainDependencyContainer(
            DependencyContainer.Error()
        )
        val manageResources: ManageResources = ManageResources.Base(this)
        val provideIDontUnderstandYouViewModelChain = ProvideIDontUnderstandYouViewModelChain(manageResources)
        val provideWeatherViewModelChain = ProvideWeatherViewModelChain(coreModule, this)
        val provideViewModelChain =
            ProvideViewModelChain.Base(provideWeatherViewModelChain, provideIDontUnderstandYouViewModelChain)
        val messagesDependencyContainer =
            MessagesDependencyContainer(main, coreModule, provideViewModelChain.viewModelChain())
        viewModelsFactory = ViewModelsFactory(messagesDependencyContainer)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner) =
        ViewModelProvider(owner, viewModelsFactory)[clazz]

}