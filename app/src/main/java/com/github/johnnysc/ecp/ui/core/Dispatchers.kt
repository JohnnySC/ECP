package com.github.johnnysc.ecp.ui.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface Dispatchers {

    fun launchUI(scope: CoroutineScope, block: suspend () -> Unit): Job
    fun launchBackground(scope: CoroutineScope, block: suspend () -> Unit): Job
    suspend fun changeToUI(block: suspend () -> Unit)

    class Base: Dispatchers {

        private val main = kotlinx.coroutines.Dispatchers.Main
        private val io = kotlinx.coroutines.Dispatchers.IO

        override fun launchUI(scope: CoroutineScope, block: suspend () -> Unit) =
            scope.launch(main) {
                block.invoke()
            }

        override fun launchBackground(scope: CoroutineScope, block: suspend () -> Unit) =
            scope.launch(io) {
                block.invoke()
            }

        override suspend fun changeToUI(block: suspend () -> Unit) =
            withContext(main) {
                block.invoke()
            }
    }
}