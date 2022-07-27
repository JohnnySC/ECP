package com.github.johnnysc.ecp.presentation.messages


import com.github.johnnysc.ecp.presentation.main.MainActivityWeatherTest
import com.github.johnnysc.ecp.presentation.messages.Messages.currentTemperatureMessageId
import com.github.johnnysc.ecp.presentation.messages.Messages.inputStringForNotDefCityID
import com.github.johnnysc.ecp.presentation.messages.Messages.inputTextId
import com.github.johnnysc.ecp.presentation.messages.Messages.sendMessage
import com.github.johnnysc.ecp.presentation.messages.Messages.setDefaultCityCommandID
import com.github.johnnysc.ecp.presentation.messages.Messages.thereIsNoCityWithSuchTitle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MessagesWeatherTest : MainActivityWeatherTest() {

    private val defaultCity = "Ekibastuz"
    private val cityName = "Almaty"
    private val noneExistedCity = "Rotrstan"

    @Test
    fun requestForTemperatureInDefaultCityWithoutDefaultCitySetAndSetDefCityAndRequireAgain(): Unit =
        runBlocking {
            internetConnection.turnOnInternet()
            Messages.apply {
                var input = inputStringForDefCityOneID.createStringFromId()

                inputTextId.typeTextToEditText(input)
                sendMessage.handleClick()
                
                checkItemText(0, input)
                checkItemText(1, thereIsnoDefaultCitySet.createStringFromId())

                input = setDefaultCityCommandID.createRequestForDefaultCitySet(defaultCity)

                inputTextId.typeTextToEditText(input)
                sendMessage.handleClick()
                
                checkItemText(2, input)
                checkItemText(
                    3,
                    defaultCitySetResultMessageId.createSuccessResponseForSetDefaultCity(defaultCity)
                )

                input = inputStringForDefCityOneID.createStringFromId()

                inputTextId.typeTextToEditText(input)
                sendMessage.handleClick()
                
                checkItemText(4, input)
                checkItemText(
                    5,
                    currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(25.8F)
                )
            }
        }

    @Test
    fun requestForTemperatureInCityByNameWithInternet() = runBlocking {
        internetConnection.turnOnInternet()
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(cityName)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        
        checkItemText(0, input)
        checkItemText(
            1,
            currentTemperatureMessageId.createSuccessResponseForTemperatureInCity(34.0F)
        )
    }

    @Test
    fun requestForDefCitySetWithNoneExistedCity(): Unit = runBlocking {
        internetConnection.turnOnInternet()
        val input = setDefaultCityCommandID.createRequestForDefaultCitySet(noneExistedCity)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        
        checkItemText(0, input)
        checkItemText(1, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestForTemperatureInCityByNameWithNoneExistedCity(): Unit = runBlocking {
        internetConnection.turnOnInternet()
        val input = inputStringForNotDefCityID.createRequestForWeatherInCity(noneExistedCity)
        inputTextId.typeTextToEditText(input)
        sendMessage.handleClick()
        checkItemText(0, input)
        checkItemText(1, thereIsNoCityWithSuchTitle.createStringFromId())
    }

    @Test
    fun requestIncomprehensibleMessage(): Unit = runBlocking {
        internetConnection.turnOnInternet()
        Messages.apply {
            inputTextId.typeTextToEditText(incorrectMessage)
            sendMessage.handleClick()
            checkItemText(0, incorrectMessage)
            checkItemText(1, iCanUnderstandYou.createStringFromId())
        }
    }
}