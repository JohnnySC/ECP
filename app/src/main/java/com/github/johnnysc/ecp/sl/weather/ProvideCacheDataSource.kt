package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.data.weather.cache.CityCacheDataSource

interface ProvideCacheDataSource {
    fun provideCacheDataSource(): CityCacheDataSource

    class Base(private val cityPreferenceDataStore: ProvideCityPreferenceDataStore) :
        ProvideCacheDataSource {
        override fun provideCacheDataSource() =
            CityCacheDataSource.Base(cityPreferenceDataStore.provideCityPreferenceDataStore())

    }
}