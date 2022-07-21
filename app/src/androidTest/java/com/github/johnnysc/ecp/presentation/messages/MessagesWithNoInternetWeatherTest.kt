package com.github.johnnysc.ecp.presentation.messages


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.github.johnnysc.ecp.presentation.main.MainActivityWeatherTest
import com.github.johnnysc.ecp.presentation.messages.Messages.iCanUnderstandYou
import com.github.johnnysc.ecp.presentation.messages.Messages.incorrectMessage
import com.github.johnnysc.ecp.presentation.messages.Messages.inputStringForNotDefCityID
import com.github.johnnysc.ecp.presentation.messages.Messages.inputTextId
import com.github.johnnysc.ecp.presentation.messages.Messages.sendMessage
import com.github.johnnysc.ecp.presentation.messages.Messages.setDefaultCityCommandID
import com.github.johnnysc.ecp.presentation.messages.Messages.theresIsNoConnectionId
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MessagesWithNoInternetWeatherTest :MainActivityWeatherTest() {

    private val defaultCity = "Ekibastuz"
    private val cityName = "Almaty"
    private val noneExistedCity = "Rotrstan"

    @Test
    fun setDefaultCityWhenNoInternetMustNoConnectionMessage(): Unit = runBlocking {
        internetConnection.turnOffInternet()
        val input = setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        checkItemText(0, input)
        checkItemText(1, theresIsNoConnectionId.createStringFromId())
    }

    @Test
    fun setDefaultCityWhenNoInternetAndCityNotExistMustNoConnectionMessage(): Unit = runBlocking {
        internetConnection.turnOffInternet()
        val input = setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        checkItemText(0, input)
        checkItemText(1, theresIsNoConnectionId.createStringFromId())
    }

    @Test
    fun weatherInCityWhenNoInternetMustNoConnectionMessage(): Unit = runBlocking {
        internetConnection.turnOffInternet()
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        checkItemText(0, input)
        checkItemText(1, theresIsNoConnectionId.createStringFromId())
    }

    @Test
    fun weatherInCityWhenNoInternetAndCityNotExistMustNoConnectionMessage(): Unit = runBlocking {
        internetConnection.turnOffInternet()
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        checkItemText(0, input)
        checkItemText(1, theresIsNoConnectionId.createStringFromId())
    }

    @Test
    fun incorrectMessageWhenNoInternetMustNoConnectionMessage(): Unit = runBlocking {
        internetConnection.turnOffInternet()
        inputTextId.typeTextToEditText(incorrectMessage)
        sendMessage.handleClick()
        checkItemText(0, incorrectMessage)
        checkItemText(1, iCanUnderstandYou.createStringFromId())
    }
}