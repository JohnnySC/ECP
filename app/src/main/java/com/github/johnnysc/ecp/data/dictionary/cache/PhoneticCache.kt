package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmObject

interface PhoneticCache {

    open class Base : RealmObject(), PhoneticCache {
        var text: String = ""
        var audio: String = ""
    }
}