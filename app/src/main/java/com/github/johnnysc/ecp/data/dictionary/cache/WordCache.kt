package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface WordCache {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(
            word: String,
            phonetics: RealmList<BasePhoneticCache>,
            meanings: RealmList<BaseMeaningCache>
        ): T
    }
}

open class BaseWordCache : RealmObject(), WordCache {
    var word: String = ""
    var phonetics: RealmList<BasePhoneticCache> = RealmList()
    var meanings: RealmList<BaseMeaningCache> = RealmList()

    override fun <T> map(mapper: WordCache.Mapper<T>) = mapper.map(word, phonetics, meanings)
}