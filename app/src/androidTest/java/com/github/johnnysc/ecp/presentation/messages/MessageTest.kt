package com.github.johnnysc.ecp.presentation.messages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
import org.junit.Test

class MessageTest : MainActivityTest() {
    private val defaultCity = "Экибастуз"

    @Test
    fun requestForTemperatureInDefaultCityWithInternet() {
        val messagesWithInternetAndDefCity = MessagesWithInternetAndDefCity()
        messagesWithInternetAndDefCity.apply {
            val inputSetDefaultCity =
                setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

            onView(withId(inputTextId)).perform(replaceText(inputSetDefaultCity))
            onView(withId(sendMessage)).perform(click())

            checkItemText(defaultCityMessageInputPosition, inputSetDefaultCity)
            checkItemText(
                successResponseForSetDefCityPosition,
                defaultCitySetResultMessage.createSuccessResponseForSetDefaultCity(defaultCity)
            )

            val getWeatherInDefCity =
                inputStringForDefCityOneID.createRequestForWeatherInDefaultCity()

            onView(withId(inputTextId)).perform(replaceText(getWeatherInDefCity))
            onView(withId(sendMessage)).perform(click())

            checkItemText(requestForWeatherInDefaultCity, getWeatherInDefCity)
            checkItemText(
                successResponseForWeatherInDefaultCity,
                currentTemperatureMessage.createSuccessResponseForTemperatureInDefCity()
            )
        }
    }

    @Test
    fun requestForTemperatureInCityWithInternet()
    {
        val messagesWithInternetAndCity=MessagesWithInternetAndCity()
        messagesWithInternetAndCity.apply {
            inputStringForNotDefCityID.createRequestForWeather(defaultCity)
        }
    }

    private fun Int.createRequestForDefaultCitySet(cityName: String) =
        "${resources.string(this)} $cityName"

    private fun Int.createRequestForWeather(cityName: String) =
        "${resources.string(this)} $cityName"

    private fun Int.createRequestForWeatherInDefaultCity() =
        resources.string(this)

    private fun Int.createSuccessResponseForSetDefaultCity(cityName: String) =
        resources.string(this).format(defaultCity)

    private fun Int.createSuccessResponseForTemperatureInDefCity() =
        resources.string(this).format(20)
}