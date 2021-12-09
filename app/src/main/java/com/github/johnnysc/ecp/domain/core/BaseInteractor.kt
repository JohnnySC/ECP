package com.github.johnnysc.ecp.domain.core

import com.github.johnnysc.ecp.ui.core.Dispatchers

abstract class BaseInteractor(
    private val dispatchers: Dispatchers,
    private val handleError: HandleError
) {

    suspend fun <T> handle(
        doAtFinish: () -> Unit,
        success: (result: T) -> Unit,
        block: suspend () -> T
    ) =
        try {
            val result: T = block.invoke()
            dispatchers.changeToUI {
                doAtFinish.invoke()
                success.invoke(result)
            }
        } catch (e: Exception) {
            dispatchers.changeToUI {
                doAtFinish.invoke()
                handleError.handle(e)
            }
        }
}