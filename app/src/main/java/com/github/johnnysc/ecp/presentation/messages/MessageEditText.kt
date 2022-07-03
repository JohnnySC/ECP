package com.github.johnnysc.ecp.presentation.messages

import android.content.Context
import android.util.AttributeSet

class MessageEditText : androidx.appcompat.widget.AppCompatEditText {

    private var listener: ((String) -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setOnSendListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    fun getFormattedText() {
        val formattedText = text?.trim()
        if (formattedText?.isNotEmpty() == true) {
            listener?.invoke(formattedText.toString())
        }
        text?.clear()
    }
}