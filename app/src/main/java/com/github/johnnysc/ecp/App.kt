package com.github.johnnysc.ecp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.core.ProvideViewModel

class App : Application(), ProvideViewModel {

    override fun <T : ViewModel> provideViewModel(
        clazz: Class<T>,
        viewModelStoreOwner: ViewModelStoreOwner
    ): T {
        TODO("Not yet implemented")
    }
}