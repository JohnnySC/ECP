package com.github.johnnysc.ecp.core

import android.content.Context
import com.github.johnnysc.coremvvm.core.ManageResources

class BaseManageResources(private val context: Context) : ManageResources {

    override fun string(id: Int): String {
        return context.getString(id)
    }
}