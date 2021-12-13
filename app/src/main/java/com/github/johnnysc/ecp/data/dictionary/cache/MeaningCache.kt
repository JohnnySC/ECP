package com.github.johnnysc.ecp.data.dictionary.cache

import com.github.johnnysc.ecp.data.dictionary.DefinitionCloud
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

interface MeaningCache{

    open class Base() : RealmObject(), MeaningCache {
        @PrimaryKey
        var partOfSpeech: String = ""
        var definitions: RealmList<DefinitionCloud> = RealmList()
    }

}

