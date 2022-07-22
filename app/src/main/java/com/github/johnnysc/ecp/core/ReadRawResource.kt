package com.github.johnnysc.ecp.core

import android.content.Context
import androidx.annotation.RawRes

interface ReadRawResource {

    fun readText(@RawRes id: Int): String

    class Mock(private val context: Context) : ReadRawResource {
        override fun readText(id: Int) =
            context.resources.openRawResource(id).bufferedReader().readText()
    }
}