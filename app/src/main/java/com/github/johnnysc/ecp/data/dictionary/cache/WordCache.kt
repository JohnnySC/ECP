package com.github.johnnysc.ecp.data.dictionary.cache


import com.github.johnnysc.ecp.data.dictionary.MeaningCloud
import com.github.johnnysc.ecp.data.dictionary.PhoneticCloud


interface WordCache {

    open class Base : RealmObject(), WordCache {
        var word: String = ""
        var phonetics: RealmList<PhoneticCache> = RealmList()
        var meanings: RealmList<MeaningCache> = RealmList()
    }
}