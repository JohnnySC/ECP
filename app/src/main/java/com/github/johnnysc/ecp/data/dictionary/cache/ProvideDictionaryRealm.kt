package com.github.johnnysc.ecp.data.dictionary.cache

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class ProvideDictionaryRealm(private val context: Context) {

    init {
        Realm.init(context)
    }

    fun realm(isDebug: Boolean = false): Realm {
        val configName: String = if (isDebug) "DictionaryAppTest" else "DictionaryApp"
        return Realm.getInstance(RealmConfiguration.Builder().name(configName).build())
    }

}