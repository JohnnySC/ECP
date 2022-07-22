package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.core.StringToObject
import com.github.johnnysc.ecp.core.ConvertRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource

abstract class ProvideConvertRawResourceToPojoAdapter<T, V>(
    protected val readRawResource: ReadRawResource,
    protected val stringToObject: StringToObject<T>
) {
    abstract fun provideConvertRawResourceToPojoAdapter(): ConvertRawResourceToPoJo<T, V>
}