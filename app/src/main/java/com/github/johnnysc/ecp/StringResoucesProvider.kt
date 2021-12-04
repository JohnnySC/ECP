package com.github.johnnysc.ecp

import androidx.annotation.StringRes

interface ProvideString {
    fun string(@StringRes resId: Int): String?

    fun string(@StringRes resId: Int, formatArg: Any?): String?
}