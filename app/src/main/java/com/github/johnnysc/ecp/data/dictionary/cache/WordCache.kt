package com.github.johnnysc.ecp.data.dictionary.cache

import com.github.johnnysc.ecp.data.dictionary.MeaningCloud
import com.github.johnnysc.ecp.data.dictionary.PhoneticCloud
import io.realm.RealmList
import io.realm.RealmObject

interface WordCache {

    open class Base : RealmObject(), WordCache {
        var word: String = ""
        var phonetics: RealmList<PhoneticCloud> = RealmList()
        var meanings: RealmList<MeaningCloud> = RealmList()
    }
}