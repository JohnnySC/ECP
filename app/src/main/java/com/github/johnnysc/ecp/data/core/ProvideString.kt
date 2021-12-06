package com.github.johnnysc.ecp.data.core

import androidx.annotation.StringRes

interface ProvideString {
    fun string(@StringRes resId: Int): String

    fun string(@StringRes resId: Int, vararg args: Any): String
}