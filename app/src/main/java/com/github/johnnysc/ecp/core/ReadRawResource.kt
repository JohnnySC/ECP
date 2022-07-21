package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes

interface ReadRawResource {

    fun readText(@RawRes id: Int): String
}