package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.data.weather.cache.CityCacheDataSource
import com.github.johnnysc.ecp.data.weather.cache.CityPreferenceDataStore

interface ProvideCacheDataSource {
    fun provideCacheDataSource(): CityCacheDataSource

    class Base(private val cityPreferenceDataStore: CityPreferenceDataStore) : ProvideCacheDataSource {

        override fun provideCacheDataSource(): CityCacheDataSource {
            return CityCacheDataSource.Base(cityPreferenceDataStore)
        }
    }
}