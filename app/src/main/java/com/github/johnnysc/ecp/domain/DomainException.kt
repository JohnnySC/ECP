package com.github.johnnysc.ecp.domain

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface DomainException {
    fun <T> map(mapper: Mapper<T>): T

    abstract class AbstractDomainException(private val messageId: Int, message: String) :
        Exception(message), DomainException {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(messageId)

    }

    class Unknown :
        AbstractDomainException(R.string.unknown_exception, "Unknown exception")

    class ThereIsNoCityWithSuchTitle : AbstractDomainException(
        R.string.there_is_no_city_with_such_title,
        "There is no city with such title"
    )

    class ThereIsNoConnection :
        AbstractDomainException(R.string.there_is_no_connection, "There is no connection")

    class ThereIsNoDefaultCity :
        AbstractDomainException(R.string.weather_no_default_city, "There is no default city")

    interface Mapper<T> {
        fun map(messageId: Int): T

        class Base(private val resources: ManageResources) : Mapper<MessageUI> {
            override fun map(messageId: Int) = MessageUI.AiError(text = resources.string(messageId))
        }
    }
}