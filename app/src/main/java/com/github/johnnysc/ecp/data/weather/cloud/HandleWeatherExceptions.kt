package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.ecp.data.cloud.HandleBaseException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import retrofit2.HttpException

class HandleWeatherExceptions : HandleBaseException() {

    override fun handle(error: Exception) = if (error is HttpException && error.code() == 404) {
        ThereIsNoCityWithSuchTitleException()
    } else {
        super.handle(error)
    }

}

