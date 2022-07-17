package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes

interface RawResourceReader {

    fun readText(@RawRes id: Int): String
}