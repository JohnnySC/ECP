package com.github.johnnysc.ecp.data

import com.github.johnnysc.coremvvm.data.HandleError

abstract class AbstractRepository(private val handleError: HandleError) {
    suspend fun <T> handle(
        request: suspend () -> T
    ): T {
        try {
            return request.invoke()
        } catch (exception: Exception) {
            throw exception
        }
    }
}