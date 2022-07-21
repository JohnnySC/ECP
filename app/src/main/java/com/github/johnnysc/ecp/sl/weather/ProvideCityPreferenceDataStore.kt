package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.data.weather.cache.CityPreferenceDataStore

interface ProvideCityPreferenceDataStore {

    fun provideCityPreferenceDataStore(): CityPreferenceDataStore

    class Base(private val coreModule: CoreModule) : ProvideCityPreferenceDataStore {
        override fun provideCityPreferenceDataStore() =
            CityPreferenceDataStore.Base(ProvideCitySharedPref(coreModule).provideSharedPreferences())
    }
}