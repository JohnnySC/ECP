package com.github.johnnysc.ecp.presentation.messages


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.presentation.main.MainActivityTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MessagesWithNoInternetTest : MainActivityTest() {
    private val defaultCity = "Екибастуз"
    private val cityName = "Алматы"
    private val noneExistedCity = "Ротрстан"

    @Test
    fun allMethodsWithoutInternetFirstPart() = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(false)
        val messages = Messages()
        messages.apply {
            var input =
                setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

            onView(ViewMatchers.withId(inputTextId))
                .perform(ViewActions.replaceText(input))
            onView(ViewMatchers.withId(sendMessage)).perform(ViewActions.click())
            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition, theresIsNoConnectionId.createStringFromId())

            input = inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
            onView(ViewMatchers.withId(inputTextId))
                .perform(ViewActions.replaceText(input))
            onView(ViewMatchers.withId(sendMessage)).perform(ViewActions.click())

            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition, theresIsNoConnectionId.createStringFromId())

            input =
                setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
            onView(ViewMatchers.withId(inputTextId))
                .perform(ViewActions.replaceText(input))
            onView(ViewMatchers.withId(sendMessage)).perform(ViewActions.click())
            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition, theresIsNoConnectionId.createStringFromId())
        }
        return@runBlocking
    }

    @Test
    fun allMethodsWithoutInternetSecondPart() = runBlocking {
        WeatherCloudDataSource.InternetAvailability.setInternetAvailable(false)
        val messages = Messages()
        messages.apply {
            val input = inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
            onView(ViewMatchers.withId(inputTextId))
                .perform(ViewActions.replaceText(input))
            onView(ViewMatchers.withId(sendMessage)).perform(ViewActions.click())
            checkItemText(userInputPos, input)
            checkItemText(aiResponsePosition, theresIsNoConnectionId.createStringFromId())
            onView(ViewMatchers.withId(inputTextId))
                .perform(ViewActions.replaceText(incorrectMessage))
            onView(ViewMatchers.withId(sendMessage)).perform(ViewActions.click())
            checkItemText(userInputPos, incorrectMessage)
            checkItemText(aiResponsePosition, iCanUnderstandYou.createStringFromId())
        }
        return@runBlocking
    }

}