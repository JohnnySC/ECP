package com.github.johnnysc.ecp.schedule.data

import java.text.SimpleDateFormat
import java.util.*

interface EventCache {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val name: String,
        private val date: Long
    ) : EventCache {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(name, date)
    }

    interface Mapper<T> {

        fun map(name: String, date: Long): T

        class ToString : Mapper<String> {

            override fun map(name: String, date: Long): String =
                "${SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(date))}: $name"
        }
    }
}