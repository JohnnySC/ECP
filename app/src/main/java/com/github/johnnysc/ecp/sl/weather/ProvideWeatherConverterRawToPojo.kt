package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.core.StringToObject
import com.github.johnnysc.ecp.core.ConvertRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.Weather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideConvertRawResourceToPojoAdapter

class ProvideWeatherConverterRawToPojo(
    stringToObject: StringToObject<Weather.Base>,
    readRawResource: ReadRawResource
) : ProvideConvertRawResourceToPojoAdapter<Weather.Base, RemoteWeather>(
    readRawResource,
    stringToObject
) {
    override fun provideConvertRawResourceToPojoAdapter(): ConvertRawResourceToPoJo<Weather.Base, RemoteWeather> {
        return WeatherCloudDataSource.Mock.FetchWeather(stringToObject, readRawResource)
    }
}