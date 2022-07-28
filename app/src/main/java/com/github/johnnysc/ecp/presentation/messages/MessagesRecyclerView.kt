package com.github.johnnysc.ecp.presentation.messages

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class MessagesRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun scrollDown() {
        adapter?.let { scrollToPosition(it.itemCount - 1) }
    }
}
