package com.github.johnnysc.ecp.data.core

interface BaseModule {

    fun <T : BaseViewModel<T>> instance(): BaseViewModel<T>

}