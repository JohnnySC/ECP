package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface MeaningCache

open class BaseMeaningCache : RealmObject(), MeaningCache {
    var partOfSpeech: String = ""
    var definitions: RealmList<BaseDefinitionCache> = RealmList()
}