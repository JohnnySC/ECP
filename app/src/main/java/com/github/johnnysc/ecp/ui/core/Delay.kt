package com.github.johnnysc.ecp.ui.core

import java.util.*

interface Delay {
    fun delay(str: String)

    class Base(
        private val time: Long = 300L,
        private val string: (String) -> Unit,
    ) : Delay {
        private var timer = Timer()
        override fun delay(str: String) {
            timer.cancel()
            timer = Timer()
            timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        string.invoke(str)
                    }
                },
                time
            )
        }
    }
}

