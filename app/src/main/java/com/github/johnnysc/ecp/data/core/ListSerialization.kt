package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>

    class Base : ListSerialization {
        private val delimiter = " "
        override fun map(list: List<String>) = if (list.size <= 1) list.joinToString()
        else list.joinToString(delimiter)

        override fun map(data: String) = data.split(delimiter)
    }
}