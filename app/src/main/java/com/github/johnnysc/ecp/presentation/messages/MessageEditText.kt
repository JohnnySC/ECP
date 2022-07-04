package com.github.johnnysc.ecp.presentation.messages

import android.content.Context
import android.util.AttributeSet

class MessageEditText : androidx.appcompat.widget.AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun getFormattedText(handleInput: HandleInput) {
        val formattedText = text?.trim()
        if (formattedText?.isNotEmpty() == true) {
            handleInput.handleInput(formattedText.toString())
        }
        text?.clear()
    }
}