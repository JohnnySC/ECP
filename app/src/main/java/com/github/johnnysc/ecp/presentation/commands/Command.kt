package com.github.johnnysc.ecp.presentation.commands

import com.github.johnnysc.ecp.domain.weather.DefaultCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

/**
 * @param T Interactor
 */
interface Command<T> : FeatureChain.Check, HandleUseCase<T> {
    /**
     * @param T-interactor
     * @param U-useCase
     */
    abstract class Abstract<T:U,U>(private val parser:Parser<U>):Command<T>
    {
        private var handler: IsEmptyUseCase<U> = IsEmptyUseCase.Empty()

        override suspend fun handle(useCase: T): MessageUI = handler.handle(useCase)

        override fun canHandle(message: String): Boolean {
            handler = parser.map(message)
            return !handler.isEmpty()
        }
    }

    class Empty<T> : Command<T> {
        override suspend fun handle(useCase: T): MessageUI = MessageUI.Empty()

        override fun canHandle(message: String): Boolean = false
    }
}