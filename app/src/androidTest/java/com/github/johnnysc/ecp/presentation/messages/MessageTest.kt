package com.github.johnnysc.ecp.presentation.messages

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val TAG = "MessageTest"
class MessageTest : MainActivityTest() {
    private val defaultCity = "Экибастуз"

    @Test
    fun requestForTemperatureInDefaultCityWithInternet() = runBlocking{
        val messagesWithInternetAndDefCity = MessagesWithInternetAndDefCity()
        messagesWithInternetAndDefCity.apply {
            val inputSetDefaultCity =
                setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

            onView(withId(inputTextId)).perform(replaceText(inputSetDefaultCity))
            onView(withId(sendMessage)).perform(click())
            delay(10000)
            checkItemText(defaultCityMessageInputPosition, inputSetDefaultCity)
            Log.d(TAG, "requestForTemperatureInDefaultCityWithInternet: ")
            checkItemText(
                successResponseForSetDefCityPosition,
                defaultCitySetResultMessage.createSuccessResponseForSetDefaultCity(defaultCity)
            )

            val getWeatherInDefCity =
                inputStringForDefCityOneID.createRequestForWeatherInDefaultCity()

            onView(withId(inputTextId)).perform(replaceText(getWeatherInDefCity))
            onView(withId(sendMessage)).perform(click())
            Log.d(TAG, "requestForTemperatureInDefaultCityWithInternet: 123")

            checkItemText(requestForWeatherInDefaultCity, getWeatherInDefCity)
            checkItemText(
                successResponseForWeatherInDefaultCity,
                currentTemperatureMessage.createSuccessResponseForTemperatureInDefCity()
            )
        }
        return@runBlocking
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