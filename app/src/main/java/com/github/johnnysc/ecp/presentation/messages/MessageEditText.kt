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

    fun handleInput(handleInput: HandleInput) {
        var formattedText = text ?: ""
        formattedText = formattedText.trim()
        if (formattedText.isNotEmpty())
            handleInput.handleInput(formattedText.toString())
        setText("")
    }
}