package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.core.ConvertFromJson
import com.github.johnnysc.ecp.core.ConverterRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource

abstract class ProvideConvertRawResourceToPojoAdapter<T, V>(
    protected val readRawResource: ReadRawResource,
    protected val convertFromJson: ConvertFromJson<T>
) {
    abstract fun provideConvertRawResourceToPojoAdapter(): ConverterRawResourceToPoJo<T, V>
}