package com.github.johnnysc.ecp.domain

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity

interface ExceptionChain {
    interface Check : ExceptionChain {
        fun check(exception: Exception): Boolean
    }

    interface Handle : HandleError

    interface CheckAndHandle : Check, Handle


    class Empty : Handle {
        override fun handle(error: Exception): Exception {
            return DomainException.UnknownDomainException()
        }

    }

    class ThereIsNoSuchCityChain : CheckAndHandle {

        override fun check(exception: Exception) = exception is ThereIsNoCityWithSuchTitle

        override fun handle(error: Exception)= DomainException.ThereIsNoCityWithSuchTitle()
    }

    class ThereIsNoConnectionChain : CheckAndHandle {

        override fun check(exception: Exception) = exception is ThereIsNoConnection

        override fun handle(error: Exception) = DomainException.ThereIsNoConnection()
    }

    class ThereIsNoDefaultCityChain : CheckAndHandle {

        override fun check(exception: Exception) = exception is ThereIsNoDefaultCity

        override fun handle(error: Exception) = DomainException.ThereIsNoDefaultCity()
    }


}