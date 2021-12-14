package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface WordCache

open class BaseWordCache : RealmObject(), WordCache {
    var word: String = ""
    var phonetics: RealmList<BasePhoneticCache> = RealmList()
    var meanings: RealmList<BaseMeaningCache> = RealmList()
}