package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.domain.ExceptionChain

interface ProvideExceptionChain {
    fun provideExceptionChain():ExceptionChain

}