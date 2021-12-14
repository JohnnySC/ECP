package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

open class DefinitionCache : RealmObject() {
    var definition: String = ""
    var example: String = ""
    var synonyms: RealmList<String> = RealmList()
    var antonyms: RealmList<String> = RealmList()
}