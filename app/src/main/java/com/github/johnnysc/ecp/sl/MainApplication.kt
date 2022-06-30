package com.github.johnnysc.ecp.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.coremvvm.sl.ViewModelsFactory
import com.github.johnnysc.ecp.sl.main.MainDependencyContainer
import com.github.johnnysc.ecp.sl.message.MessagesDependencyContainer

class MainApplication : Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        val coreModule = CoreModule.Base(this)
        val main = MainDependencyContainer(
            DependencyContainer.Error(),
            coreModule
        )
        val messagesDependencyContainer = MessagesDependencyContainer(main, coreModule)
        viewModelsFactory = ViewModelsFactory(messagesDependencyContainer)
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner) =
        ViewModelProvider(owner, viewModelsFactory)[clazz]

}