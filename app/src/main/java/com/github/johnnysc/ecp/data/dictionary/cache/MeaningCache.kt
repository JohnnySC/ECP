package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface MeaningCache {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(partOfSpeech: String, definitions: List<MeaningCache>): T
    }
}

open class BaseMeaningCache : RealmObject(), MeaningCache {
    var partOfSpeech: String = ""
    var definitions: RealmList<MeaningCache> = RealmList()

    override fun <T> map(mapper: MeaningCache.Mapper<T>) = mapper.map(partOfSpeech, definitions)

}