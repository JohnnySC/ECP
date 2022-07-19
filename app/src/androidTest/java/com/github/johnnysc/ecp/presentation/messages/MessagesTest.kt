package com.github.johnnysc.ecp.presentation.messages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
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
import com.github.johnnysc.ecp.presentation.messages.Messages.thereIsnoDefaultCitySet
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MessagesTest : MainActivityTest() {

    private val defaultCity = "Екибастуз"
    private val cityName = "Алматы"
    private val noneExistedCity = "Ротрстан"

    @Test
    fun requestForTemperatureInDefaultCityWithoutDefaultCitySet() = runBlocking {
        internetConnection.changeInternetAvailable(true)

        val getWeatherInDefCity = inputStringForDefCityOneID.createStringFromId()

        onView(withId(inputTextId)).perform(typeText(getWeatherInDefCity))
        onView(withId(sendMessage)).perform(click())

        checkItemText(0, getWeatherInDefCity)
        checkItemText(1, thereIsnoDefaultCitySet.createStringFromId())
    }

    @Test
    fun requestForTemperatureInDefaultCityWithInternet() = runBlocking {
        internetConnection.changeInternetAvailable(true)
        val input = setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(0, input)
        checkItemText(1, defaultCitySetResultMessageId.createSuccessResponseForSetDefaultCity(defaultCity))
        val getWeatherInDefCity = inputStringForDefCityOneID.createStringFromId()

        onView(withId(inputTextId)).perform(replaceText(getWeatherInDefCity))
        onView(withId(sendMessage)).perform(click())

        checkItemText(2, getWeatherInDefCity)
        checkItemText(3, currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(25.8F))
    }

    @Test
    fun requestForTemperatureInCityByNameWithInternet() = runBlocking {
        internetConnection.changeInternetAvailable(true)
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())

        checkItemText(0, input)
        checkItemText(1, currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(34.0F))
    }

    @Test
    fun requestForDefCitySetWithNoneExistedCity(): Unit = runBlocking {
        internetConnection.changeInternetAvailable(true)
        val input = setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(0, input)
        checkItemText(1, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestForTemperatureInCityByNameWithNoneExistedCity(): Unit = runBlocking {
        internetConnection.changeInternetAvailable(true)
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
        onView(withId(inputTextId)).perform(replaceText(input))
        onView(withId(sendMessage)).perform(click())
        checkItemText(0, input)
        checkItemText(1, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestIncomprehensibleMessage(): Unit = runBlocking {
        internetConnection.changeInternetAvailable(true)
        onView(withId(inputTextId)).perform(replaceText(incorrectMessage))
        onView(withId(sendMessage)).perform(click())
        checkItemText(0, incorrectMessage)
        checkItemText(1, iCanUnderstandYou.createStringFromId())
    }
}