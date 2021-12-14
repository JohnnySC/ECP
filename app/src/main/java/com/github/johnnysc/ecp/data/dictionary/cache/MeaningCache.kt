package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

open class MeaningCache : RealmObject() {
    var partOfSpeech: String = ""
    var definitions: RealmList<DefinitionCache> = RealmList()
}