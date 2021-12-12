package com.github.johnnysc.ecp.ui.core

import android.text.Editable
import android.text.TextWatcher

abstract class AfterTextChanged : TextWatcher {
    override fun beforeTextChanged(var1: CharSequence, var2: Int, var3: Int, var4: Int) = Unit

    override fun onTextChanged(var1: CharSequence, var2: Int, var3: Int, var4: Int) = Unit

    override fun afterTextChanged(var1: Editable) = Unit

}