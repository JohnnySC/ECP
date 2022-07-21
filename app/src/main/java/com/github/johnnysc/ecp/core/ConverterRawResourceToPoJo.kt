package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes

abstract class ConverterRawResourceToPoJo<O,W>(
    private val readRawResource: ReadRawResource,
    private val converter: Converter<O>
) {

    fun convert(@RawRes id:Int):W
    {
        val resource=readRawResource.readText(id)
        return wrapResult(converter.convert(resource))
    }

    protected abstract fun wrapResult(result: O):W

}