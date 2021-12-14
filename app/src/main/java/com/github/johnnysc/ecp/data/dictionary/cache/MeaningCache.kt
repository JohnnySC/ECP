package com.github.johnnysc.ecp.data.dictionary.cache

import com.github.johnnysc.ecp.data.dictionary.DefinitionCloud
import io.realm.RealmList
import io.realm.RealmObject

interface MeaningCache {
    fun <T> map(mapper: Mapper<T>): T

    open class Base : RealmObject(), MeaningCache {
        var partOfSpeech: String = ""
        var definitions: RealmList<DefinitionCloud> = RealmList()
        override fun <T> map(mapper: Mapper<T>) = mapper.map(partOfSpeech, definitions)
    }

    interface Mapper<T> {
        fun map(partOfSpeech: String, definitions: RealmList<DefinitionCloud>): T
    }
}