package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.core.Converter
import com.github.johnnysc.ecp.core.ConverterRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource

abstract class ProvideConvertRawResourceToPojoAdapter<T, V>(
    protected val readRawResource: ReadRawResource,
    protected val converter: Converter<T>
) {
    abstract fun provideConvertRawResourceToPojoAdapter(): ConverterRawResourceToPoJo<T, V>
}