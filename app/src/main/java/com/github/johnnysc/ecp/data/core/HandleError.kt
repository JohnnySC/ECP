package com.github.johnnysc.ecp.data.core

interface HandleError {
    fun handle(exception: Exception)
}