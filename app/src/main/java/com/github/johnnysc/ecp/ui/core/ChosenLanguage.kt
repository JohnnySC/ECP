package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.core.ProvidePreferences
import com.github.johnnysc.ecp.core.Save
import com.github.johnnysc.ecp.data.core.PreferenceDataSource
import com.github.johnnysc.ecp.data.core.Read

interface ChosenLanguage : Read<String>, Save<String> {
    class Base(providePreferences: ProvidePreferences) : ChosenLanguage {

        private val sharedPreferences = providePreferences.providePreferences(FILE_NAME)
        private val preferenceDataSource = PreferenceDataSource.Base(sharedPreferences)

        override fun save(data: String) = preferenceDataSource.save(KEY, data)
        override fun read() = preferenceDataSource.read(KEY, "en")

        private companion object {
            const val FILE_NAME = "chosenLanguageFileName"
            const val KEY = "chosenLanguageKey"
        }
    }
}