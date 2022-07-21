package com.github.johnnysc.ecp.data.weather.cloud


import android.util.Log
import com.github.johnnysc.ecp.data.cloud.HandleBaseException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import retrofit2.HttpException

private const val TAG = "HandleWeatherExceptions"
class HandleWeatherExceptions : HandleBaseException() {

    override fun handle(error: Exception): Exception {
        Log.d(TAG, "handle: $error")
        val result:Exception
        if (error is HttpException &&error.code() == 404) {
            result=ThereIsNoCityWithSuchTitleException()
        }
        else {
            result = super.handle(error)
        }
        return result
    }
}
