package com.github.johnnysc.ecp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideViewModel {

    fun <T : ViewModel> provideViewModel (clazz: Class<T>, viewModelStoreOwner : ViewModelStoreOwner ) : T

}