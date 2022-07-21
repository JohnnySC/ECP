package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.core.Converter
import com.github.johnnysc.ecp.core.ConverterRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource

abstract class ProvideConvertRawResourceToPojoAdapter<O,W>(protected val readRawResource: ReadRawResource,
                                                      protected val converter: Converter<O>)
{
    abstract fun provideConvertRawResourceToPojoAdapter():ConverterRawResourceToPoJo<O,W>
}