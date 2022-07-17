package com.github.johnnysc.ecp.data.weather.cloud


import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import retrofit2.HttpException
import java.net.UnknownHostException


class HandleExceptions : HandleError {
    override fun handle(error: Exception) = when (error) {
        is UnknownHostException -> NoInternetConnectionException()
        is HttpException -> ThereIsNoCityWithSuchTitleException()
        else -> ServiceUnavailableException()
    }
}
