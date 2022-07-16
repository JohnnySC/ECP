package com.github.johnnysc.ecp.presentation.messages

import com.github.johnnysc.ecp.R

class MessagesWithInternetAndDefCity:ViewsIdKeeper() {
    val inputStringForDefCityOneID:Int=R.string.what_is_weather_like
    val setDefaultCityCommandID=R.string.set_weather_command_start
    val defaultCitySetResultMessage=R.string.set_weather_command_success
    val currentTemperatureMessage=R.string.weather_response
    val defaultCityMessageInputPosition=0
    val successResponseForSetDefCityPosition=0
    val requestForWeatherInDefaultCity=0
    val successResponseForWeatherInDefaultCity=0
}