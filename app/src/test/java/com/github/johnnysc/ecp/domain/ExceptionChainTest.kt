package com.github.johnnysc.ecp.domain

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import org.junit.Assert.assertEquals
import org.junit.Test

class ExceptionChainTest {

    private val chain = ExceptionChain.ThereIsNoSuchCityChain(
        ExceptionChain.ThereIsNoDefaultCityChain(
            ExceptionChain.ThereIsNoConnectionChain(
                ExceptionChain.DefaultExceptionChain()
            )
        )
    )
    private val mapper = DomainException.Mapper.Base(TestManageResource())

    @Test
    fun `test exception chain with standard exceptions from data layer of weather feature`() {
        val expectedOne = MessageUI.AiError("There is no connection")
        val expectedTwo = MessageUI.AiError("There is no city with such title")
        val expectedThree =
            MessageUI.AiError("Error! Set default city using this command: My city is X, where X is the name of the city")

        val inputOne = NoInternetConnectionException()
        val inputTwo = ThereIsNoCityWithSuchTitleException()
        val inputThree = ThereIsNoDefaultCityException()

        val actualOne = chain.handle(inputOne).map(mapper)
        assertEquals(expectedOne, actualOne)
        val actualTwo = chain.handle(inputTwo).map(mapper)
        assertEquals(expectedTwo, actualTwo)
        val actualThree = chain.handle(inputThree).map(mapper)
        assertEquals(expectedThree, actualThree)

    }

    @Test
    fun `test exception chain with unknown exception`() {
        val expected = MessageUI.AiError("Unknown exception")
        val input = IndexOutOfBoundsException()
        val actual = chain.handle(input).map(mapper)
        assertEquals(expected, actual)
    }

    class TestManageResource : ManageResources {
        override fun string(id: Int): String {
            return when (id) {
                R.string.unknown_exception -> "Unknown exception"
                R.string.there_is_no_city_with_such_title -> "There is no city with such title"
                R.string.there_is_no_connection -> "There is no connection"
                R.string.weather_no_default_city -> "Error! Set default city using this command: My city is X, where X is the name of the city"
                else -> "Something went wrong"
            }
        }
    }
}