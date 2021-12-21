package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.core.ChooseLanguages
import com.github.johnnysc.ecp.core.ProvidePreferences
import com.github.johnnysc.ecp.core.Save
import com.github.johnnysc.ecp.core.SelectedLanguage
import com.github.johnnysc.ecp.data.core.PreferenceDataSource
import com.github.johnnysc.ecp.data.core.Read

interface ChosenLanguage : ChooseLanguages, SelectedLanguage, Read<String>, Save<String> {

    abstract class Abstract(
        preferencesProvider: ProvidePreferences,
        filename: String,
        private val key: String
    ) : ChosenLanguage {
        private val sharedPreferences = preferencesProvider.providePreferences(filename)
        private val preferencesStore = PreferenceDataSource.Base(sharedPreferences)

        override fun save(data: String) = preferencesStore.save(key, data)
        override fun read(): String = preferencesStore.read(key, ENGLISH)

        override fun chooseEnglish() = save(ENGLISH)
        override fun chooseSpanish() = save(SPANISH)
        override fun chooseFrench() = save(FRENCH)
        override fun chooseJapanese() = save(JAPANESE)
        override fun chooseGerman() = save(GERMAN)
        override fun chooseRussian() = save(RUSSIAN)
        override fun chooseItalian() = save(ITALIAN)
        override fun chooseKorean() = save(KOREAN)

        override fun isSelectedEnglish() = read() == ENGLISH
        override fun isSelectedSpanish() = read() == SPANISH
        override fun isSelectedFrench() = read() == FRENCH
        override fun isSelectedJapanese() = read() == JAPANESE
        override fun isSelectedGerman() = read() == GERMAN
        override fun isSelectedRussian() = read() == RUSSIAN
        override fun isSelectedItalian() = read() == ITALIAN
        override fun isSelectedKorean() = read() == KOREAN

        private companion object {
            const val ENGLISH = "en"
            const val SPANISH = "es"
            const val FRENCH = "fr"
            const val JAPANESE = "ja"
            const val GERMAN = "de"
            const val RUSSIAN = "ru"
            const val ITALIAN = "it"
            const val KOREAN = "ko"
        }
    }

    class Base(preferences: ProvidePreferences) : Abstract(preferences, FILE_NAME, KEY) {
        private companion object {
            const val FILE_NAME = "languagesFileName"
            const val KEY = "languagesKey"
        }
    }
}