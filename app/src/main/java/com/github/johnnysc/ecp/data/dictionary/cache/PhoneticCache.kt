package com.github.johnnysc.ecp.data.dictionary.cache

import io.realm.RealmObject

interface PhoneticCache {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(
            text: String,
            audio: String
        ): T
    }
}

open class BasePhoneticCache : RealmObject(), PhoneticCache {
    var text: String = ""
    var audio: String = ""

    override fun <T> map(mapper: PhoneticCache.Mapper<T>) = mapper.map(text, audio)
}