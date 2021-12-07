package com.github.johnnysc.ecp.ui.core

import com.github.johnnysc.ecp.core.Mapper.UnitMapper

interface Communication<T> : Observe<T>, UnitMapper<T> {
}