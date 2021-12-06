package com.github.johnnysc.ecp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get

interface ProvideViewModel {

    fun <T : ViewModel> providingViewModel (clazz: Class<T>, viewModelStoreOwner : ViewModelStoreOwner ) : T

}