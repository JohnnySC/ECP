package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface DefinitionCache {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(
            definition: String,
            example: String,
            synonyms: List<String>,
            antonyms: List<String>
        ): T
    }
}

open class BaseDefinitionCache : RealmObject(), DefinitionCache {
    var definition: String = ""
    var example: String = ""
    var synonyms: RealmList<String> = RealmList()
    var antonyms: RealmList<String> = RealmList()

    override fun <T> map(mapper: DefinitionCache.Mapper<T>) =
        mapper.map(definition, example, synonyms, antonyms)
}