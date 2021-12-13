package com.github.johnnysc.ecp.ui.core

import java.util.*

interface Delay {
    fun delay(text: String)

    class Base(
        private val time: Long = 300L,
        private val block: (String) -> Unit,
    ) : Delay {
        private var timer = Timer()
        override fun delay(text: String) {
            timer.cancel()
            timer = Timer()
            timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        block.invoke(text)
                    }
                },
                time
            )
        }
    }
}

