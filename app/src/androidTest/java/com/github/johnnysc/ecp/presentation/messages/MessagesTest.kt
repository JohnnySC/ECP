package com.github.johnnysc.ecp.presentation.messages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MessagesTest : MainActivityTest() {
    private val defaultCity = "Екибастуз"
    private val cityName = "Алматы"
    private val noneExistedCity="Ротрстан"

    @Test
    fun requestForTemperatureInDefaultCityWithInternet() = runBlocking{
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val messages = Messages()
        messages.apply {
            val input =
                setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

            onView(withId(inputTextId)).perform(replaceText(input))
            onView(withId(sendMessage)).perform(click())
            checkItemText(userInputPos, input)
            checkItemText(
                aiResponsePosition,
                defaultCitySetResultMessageId.createSuccessResponseForSetDefaultCity(defaultCity)
            )
            val getWeatherInDefCity =
                inputStringForDefCityOneID.createStringFromId()

            onView(withId(inputTextId)).perform(replaceText(getWeatherInDefCity))
            onView(withId(sendMessage)).perform(click())

            checkItemText(userInputPos, getWeatherInDefCity)
            checkItemText(
                aiResponsePosition,
                currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(25.8F)
            )
        }
        return@runBlocking
    }

    @Test
    fun requestForTemperatureInCityByNameWithInternet() = runBlocking()
    {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val messages=Messages()
        messages.apply {
            val input=inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
            onView(withId(inputTextId)).perform(replaceText(input))
            onView(withId(sendMessage)).perform(click())

            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition,currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(34.0F))

        }
        return@runBlocking
    }

    @Test
    fun requestForDefCitySetWithNoneExistedCity()= runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val messages=Messages()
        messages.apply {
            val input =
                setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
            onView(withId(inputTextId)).perform(replaceText(input))
            onView(withId(sendMessage)).perform(click())
            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition,thereIsNoCityWithSuchTitle.createStringFromId())

        }
        return@runBlocking

    }

    @Test
    fun requestForTemperatureInCityByNameWithNoneExistedCity()= runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val messages=Messages()
        messages.apply {
            val input=inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
            onView(withId(inputTextId)).perform(replaceText(input))
            onView(withId(sendMessage)).perform(click())
            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition,thereIsNoCityWithSuchTitle.createStringFromId())
        }
        return@runBlocking
    }

    @Test
    fun requestIncomprehensibleMessage()= runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val messages=Messages()
        messages.apply {
            onView(withId(inputTextId)).perform(replaceText(incorrectMessage))
            onView(withId(sendMessage)).perform(click())
            checkItemText(userInputPos, incorrectMessage)
            checkItemText(aiResponsePosition,iCanUnderstandYou.createStringFromId())
        }
        return@runBlocking
    }


}