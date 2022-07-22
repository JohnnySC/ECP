package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes

abstract class ConverterRawResourceToPoJo<T, V>(
    private val readRawResource: ReadRawResource,
    private val convertFromJson: ConvertFromJson<T>
) {

    fun convert(@RawRes id: Int): V {
        val resource = readRawResource.readText(id)
        return wrapResult(convertFromJson.map(resource))
    }

    protected abstract fun wrapResult(result: T): V
}