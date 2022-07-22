package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.core.ConvertFromJson
import com.github.johnnysc.ecp.core.ConvertRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.Weather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideConvertRawResourceToPojoAdapter

class ProvideWeatherConverterRawToPojo(
    convertFromJson: ConvertFromJson<Weather.Base>,
    readRawResource: ReadRawResource
) : ProvideConvertRawResourceToPojoAdapter<Weather.Base, RemoteWeather>(
    readRawResource,
    convertFromJson
) {
    override fun provideConvertRawResourceToPojoAdapter(): ConvertRawResourceToPoJo<Weather.Base, RemoteWeather> {
        return WeatherCloudDataSource.Mock.FetchWeather(convertFromJson, readRawResource)
    }
}