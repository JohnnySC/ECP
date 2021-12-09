package com.github.johnnysc.ecp.domain.core

interface HandleError {
    fun handle(exception: Exception)
}