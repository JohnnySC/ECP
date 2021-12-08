package com.github.johnnysc.ecp.data.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {

    fun viewModel(): T

}