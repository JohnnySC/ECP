package com.github.johnnysc.ecp.core

import androidx.annotation.RawRes
import com.github.johnnysc.coremvvm.core.Mapper

abstract class ConvertRawResourceToPoJo<T, V>(
    private val readRawResource: ReadRawResource,
    private val stringToObject: StringToObject<T>
) : Mapper<Int, V> {

    override fun map(@RawRes data: Int): V {
        val resource = readRawResource.readText(data)
        return wrapResult(stringToObject.map(resource))
    }

    protected abstract fun wrapResult(result: T): V
}