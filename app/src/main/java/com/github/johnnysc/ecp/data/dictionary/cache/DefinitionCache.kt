package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmList
import io.realm.RealmObject

interface DefinitionCache {

    open class Base : RealmObject(), DefinitionCache {
        var definition: String = ""
        var example: String = ""
        var synonyms: RealmList<String>? = RealmList()
        var antonyms: RealmList<String>? = RealmList()
    }
}