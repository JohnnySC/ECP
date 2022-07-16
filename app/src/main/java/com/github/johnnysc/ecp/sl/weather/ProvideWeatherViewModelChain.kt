package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.data.weather.cache.CityPreferenceDataStore
import com.github.johnnysc.ecp.presentation.weather.WeatherChain
import com.github.johnnysc.ecp.presentation.weather.WeatherViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain
import retrofit2.converter.gson.GsonConverterFactory

class ProvideWeatherViewModelChain(
    private val coreModule: CoreModule,
    private val manageResources: ManageResources
) : ProvideViewModelChain<WeatherViewModelChain> {
    private val sharedPrefsKey="weather"
    override fun viewModelChain(): WeatherViewModelChain {
        val provideWeatherCloud=ProvideWeatherCloud.Base(coreModule)
        val provideWeatherCloudDataSource=ProvideWeatherCloudDataSource.Base(provideWeatherCloud)
        val cityPreferenceDataStore=CityPreferenceDataStore.Base(coreModule.sharedPreferences(sharedPrefsKey))
        val provideCacheDataSource=ProvideCacheDataSource.Base(cityPreferenceDataStore)
        val provideWeatherRepository=ProvideWeatherRepository.Base(provideWeatherCloudDataSource,provideCacheDataSource)
        val provideWeatherExceptionChain=ProvideWeatherExceptionChain()
        val provideWeatherInteractor=ProvideWeatherInteractor.Base(provideWeatherRepository,manageResources,provideWeatherExceptionChain)
        return WeatherViewModelChain(
            WeatherChain(
                provideWeatherInteractor.provideWeatherInteractor(),
                manageResources
            )
        )
    }


}