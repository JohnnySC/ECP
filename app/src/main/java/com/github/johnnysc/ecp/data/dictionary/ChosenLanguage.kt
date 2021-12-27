package com.github.johnnysc.ecp.data.dictionary

import com.github.johnnysc.ecp.data.core.Save
import com.github.johnnysc.ecp.data.core.PreferenceDataSource
import com.github.johnnysc.ecp.data.core.Read

interface ChosenLanguage : Read<String>, Save<String> {
    class Base(private val preferenceDataSource: PreferenceDataSource) : ChosenLanguage {

        override fun save(data: String) = preferenceDataSource.save(KEY, data)
        override fun read() = preferenceDataSource.read(KEY, "en")

        companion object {
            private const val KEY = "chosenLanguageKey"
        }
    }
}