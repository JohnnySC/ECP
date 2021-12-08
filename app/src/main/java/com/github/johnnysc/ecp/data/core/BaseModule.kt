package com.github.johnnysc.ecp.data.core

interface BaseModule<T : BaseViewModel<T>> {

    fun viewModel(): T

}