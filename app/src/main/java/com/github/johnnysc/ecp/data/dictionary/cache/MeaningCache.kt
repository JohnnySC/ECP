package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface MeaningCache {
    fun <T> map(mapper: BaseMeaningCache.Mapper<T>): T
}

open class BaseMeaningCache : RealmObject(), MeaningCache {
    var partOfSpeech: String = ""
    var definitions: RealmList<BaseDefinitionCache> = RealmList()
    override fun <T> map(mapper: Mapper<T>) = mapper.map(partOfSpeech, definitions)

    interface Mapper<T> {
        fun map(partOfSpeech: String, definitions: RealmList<BaseDefinitionCache>): T
    }
}