package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes
import com.google.gson.reflect.TypeToken

abstract class ConverterRawResourceToPoJo<T, V>(
    private val readRawResource: ReadRawResource,
    private val converter: Converter
) {

    fun convert(@RawRes id: Int, typeToken: TypeToken<T>): V {
        val resource = readRawResource.readText(id)
        return wrapResult(converter.convert(resource, typeToken))
    }

    protected abstract fun wrapResult(result: T): V
}