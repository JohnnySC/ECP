package com.github.johnnysc.ecp.ui.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DispatchersImpl : Dispatchers {

    private val MAIN = kotlinx.coroutines.Dispatchers.Main
    private val IO = kotlinx.coroutines.Dispatchers.IO

    override fun launchUI(scope: CoroutineScope, block: suspend () -> Unit): Job {
        return scope.launch(MAIN) {
            block.invoke()
        }
    }

    override fun launchBackground(scope: CoroutineScope, block: suspend () -> Unit): Job {
        return scope.launch(IO) {
            block.invoke()
        }
    }

    override suspend fun changeToUI(block: suspend () -> Unit) {
        return withContext(MAIN) {
            block.invoke()
        }
    }
}