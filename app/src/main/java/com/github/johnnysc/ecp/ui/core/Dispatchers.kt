package com.github.johnnysc.ecp.ui.core

import kotlinx.coroutines.*

interface Dispatchers {

    fun launchUI(scope: CoroutineScope, block: suspend () -> Unit): Job
    fun launchBackground(scope: CoroutineScope, block: suspend () -> Unit): Job
    suspend fun changeToUI(block: suspend () -> Unit)
}