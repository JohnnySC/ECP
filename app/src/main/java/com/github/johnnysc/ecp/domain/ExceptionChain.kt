package com.github.johnnysc.ecp.domain

import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity

interface ExceptionChain {
    fun handle(error: Exception): DomainException

    abstract class Base(private val nextExceptionChain: ExceptionChain) : ExceptionChain {

        protected abstract val exceptionClass: Class<out Exception>

        override fun handle(error: Exception) = if (error.javaClass == exceptionClass)
            createDomainException()
        else
            nextExceptionChain.handle(error)

        protected abstract fun createDomainException(): DomainException
    }

    class ThereIsNoSuchCityChain(exceptionChain: ExceptionChain) : Base(exceptionChain) {

        override val exceptionClass = ThereIsNoCityWithSuchTitle::class.java

        override fun createDomainException() = DomainException.ThereIsNoCityWithSuchTitle()
    }

    class ThereIsNoConnectionChain(exceptionChain: ExceptionChain) : Base(exceptionChain) {

        override val exceptionClass = ThereIsNoConnection::class.java

        override fun createDomainException() = DomainException.ThereIsNoConnection()

    }

    class ThereIsNoDefaultCityChain(exceptionChain: ExceptionChain) : Base(exceptionChain) {

        override val exceptionClass = ThereIsNoDefaultCity::class.java

        override fun createDomainException() = DomainException.ThereIsNoDefaultCity()
    }

    class DefaultExceptionChain : ExceptionChain {
        override fun handle(error: Exception) = DomainException.Unknown()
    }


}