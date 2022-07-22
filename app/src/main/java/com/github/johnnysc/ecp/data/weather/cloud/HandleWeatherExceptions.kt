package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.ecp.data.cloud.HandleBaseException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import retrofit2.HttpException


class HandleWeatherExceptions : HandleBaseException() {
    companion object {
        private const val LOCATION_INCORRECT_ERROR_PREFIX =
            "Invalid location found. Please check your location parameter:"
    }

    override fun handle(error: Exception) =
        if (error is HttpException && error.response()?.errorBody()?.string()?.startsWith(
                LOCATION_INCORRECT_ERROR_PREFIX
            ) ?: false
        ) {
            ThereIsNoCityWithSuchTitleException()
        } else {
            super.handle(error)
        }

}

