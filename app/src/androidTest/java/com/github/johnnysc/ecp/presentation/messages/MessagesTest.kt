package com.github.johnnysc.ecp.presentation.messages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
import com.github.johnnysc.ecp.presentation.messages.Messages.aiResponsePosition
import com.github.johnnysc.ecp.presentation.messages.Messages.currentTemperatureMessageId
import com.github.johnnysc.ecp.presentation.messages.Messages.defaultCitySetResultMessageId
import com.github.johnnysc.ecp.presentation.messages.Messages.iCanUnderstandYou
import com.github.johnnysc.ecp.presentation.messages.Messages.incorrectMessage
import com.github.johnnysc.ecp.presentation.messages.Messages.inputStringForDefCityOneID
import com.github.johnnysc.ecp.presentation.messages.Messages.inputStringForNotDefCityID
import com.github.johnnysc.ecp.presentation.messages.Messages.inputTextId
import com.github.johnnysc.ecp.presentation.messages.Messages.sendMessage
import com.github.johnnysc.ecp.presentation.messages.Messages.setDefaultCityCommandID
import com.github.johnnysc.ecp.presentation.messages.Messages.thereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.presentation.messages.Messages.userInputPos
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MessagesTest : MainActivityTest() {

    private val defaultCity = "Екибастуз"
    private val cityName = "Алматы"
    private val noneExistedCity = "Ротрстан"

    @Test
    fun requestForTemperatureInDefaultCityWithInternet() = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val input =
            setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(userInputPos, input)
        checkItemText(
            aiResponsePosition,
            defaultCitySetResultMessageId.createSuccessResponseForSetDefaultCity(defaultCity)
        )
        val getWeatherInDefCity = inputStringForDefCityOneID.createStringFromId()

        onView(withId(inputTextId)).perform(replaceText(getWeatherInDefCity))
        onView(withId(sendMessage)).perform(click())

        checkItemText(userInputPos, getWeatherInDefCity)
        checkItemText(
            aiResponsePosition,
            currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(25.8F)
        )
    }

    @Test
    fun requestForTemperatureInCityByNameWithInternet() = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())

        checkItemText(userInputPos, input)
        checkItemText(
            aiResponsePosition,
            currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(34.0F)
        )
    }

    @Test
    fun requestForDefCitySetWithNoneExistedCity(): Unit = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val input =
            setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(userInputPos, input)
        checkItemText(aiResponsePosition, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestForTemperatureInCityByNameWithNoneExistedCity(): Unit = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(userInputPos, input)
        checkItemText(aiResponsePosition, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestIncomprehensibleMessage(): Unit = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(true)
        onView(withId(inputTextId)).perform(replaceText(incorrectMessage))
        onView(withId(sendMessage)).perform(click())
        checkItemText(userInputPos, incorrectMessage)
        checkItemText(aiResponsePosition, iCanUnderstandYou.createStringFromId())
    }
}