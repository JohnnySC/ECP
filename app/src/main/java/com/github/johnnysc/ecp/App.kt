package com.github.johnnysc.ecp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.core.ProvideViewModel

class App : Application(), ProvideViewModel {

    override fun <T : ViewModel> providingViewModel(
        clazz: Class<T>,
        viewModelStoreOwner: ViewModelStoreOwner
    ): T = ViewModelProvider(viewModelStoreOwner).get(clazz)
}