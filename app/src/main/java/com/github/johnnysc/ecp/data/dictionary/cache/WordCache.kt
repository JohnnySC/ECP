package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface WordCache

open class WordCacheBase : RealmObject(), WordCache {
    var word: String = ""
    var phonetics: RealmList<PhoneticCache> = RealmList()
    var meanings: RealmList<MeaningCache> = RealmList()
}