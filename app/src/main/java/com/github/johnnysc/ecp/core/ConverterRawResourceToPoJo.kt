package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes

abstract class ConverterRawResourceToPoJo<T, V>(
    private val readRawResource: ReadRawResource,
    private val converter: Converter<T>
) {

    fun convert(@RawRes id: Int): V {
        val resource = readRawResource.readText(id)
        return wrapResult(converter.convert(resource))
    }

    protected abstract fun wrapResult(result: T): V
}