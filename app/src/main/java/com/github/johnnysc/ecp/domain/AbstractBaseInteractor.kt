package com.github.johnnysc.ecp.domain

import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.messages.MessageUI

abstract class AbstractBaseInteractor(
    private val handleException: ExceptionChain,
    private val domainExceptionToMessageUIMapper: DomainException.Mapper<MessageUI>
)  {

    suspend fun handle(executeRequest: suspend () -> MessageUI): MessageUI {
        val result: MessageUI = try {
            executeRequest.invoke()
        } catch (exception: Exception) {
            handleException.handle(exception).map(
                domainExceptionToMessageUIMapper
            )
        }
        return result
    }
}