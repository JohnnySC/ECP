package com.github.johnnysc.ecp.data.core

import io.realm.Realm

interface ProvideRealm {
    fun realm() : Realm
}