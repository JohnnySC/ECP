package com.github.johnnysc.ecp.sl.weather

import android.content.Context

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.BuildConfig.USE_MOCKS

import com.github.johnnysc.ecp.data.weather.cache.CityPreferenceDataStore
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.presentation.weather.WeatherChain
import com.github.johnnysc.ecp.presentation.weather.WeatherViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain
import com.google.gson.Gson


class ProvideWeatherViewModelChain(
    private val coreModule: CoreModule,
    private val context: Context
) : ProvideViewModelChain<WeatherViewModelChain> {

    override fun viewModelChain(): WeatherViewModelChain {
        val manageResources = ManageResources.Base(context)
        val provideWeatherCloudDataSource =
            if (USE_MOCKS) {
                val mockedRes = WeatherCloudDataSource.MockData(context)
                ProvideWeatherCloudDataSource.Mock(Gson(), mockedRes,coreModule)
            } else {
                val provideWeatherCloud = ProvideWeatherCloud.Base(coreModule)
                ProvideWeatherCloudDataSource.Base(provideWeatherCloud)
            }
        val provideCityPreferenceDataStore =
         ProvideCityPreferenceDataStore.Base(coreModule)
        val provideCacheDataSource = ProvideCacheDataSource.Base(provideCityPreferenceDataStore)
        val provideWeatherRepository =
            ProvideWeatherRepository.Base(provideWeatherCloudDataSource, provideCacheDataSource)
        val provideWeatherExceptionChain = ProvideWeatherExceptionChain()
        val provideWeatherInteractor = ProvideWeatherInteractor.Base(
            provideWeatherRepository,
            manageResources,
            provideWeatherExceptionChain
        )
        return WeatherViewModelChain(
            WeatherChain(
                provideWeatherInteractor.provideWeatherInteractor(),
                manageResources
            )
        )
    }
}