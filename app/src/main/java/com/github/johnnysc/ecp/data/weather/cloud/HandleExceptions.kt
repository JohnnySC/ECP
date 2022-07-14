package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
import com.github.johnnysc.ecp.domain.DomainException
import com.google.gson.stream.MalformedJsonException
import java.net.UnknownHostException

class HandleExceptions : HandleError {
    override fun handle(error: Exception) = when (error) {
        is UnknownHostException -> NoInternetConnectionException()
        is MalformedJsonException->ThereIsNoDefaultCityException()
        else-> ServiceUnavailableException()
    }
}
