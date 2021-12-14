package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmObject

open class PhoneticCache : RealmObject() {
    var text: String = ""
    var audio: String = ""
}