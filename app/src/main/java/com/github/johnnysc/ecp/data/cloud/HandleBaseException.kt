package com.github.johnnysc.ecp.data.cloud

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class HandleBaseException:HandleError {
    override fun handle(error: Exception)= when (error) {
        is UnknownHostException, is SocketTimeoutException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }

}