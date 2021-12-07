package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>

    class Base : ListSerialization {
        override fun map(list: List<String>): String {
            var convertedString = ""
            when (list.isNotEmpty()) {
                list.size > 1 -> list.forEach { convertedString += it + DELIMITER }
                list.size == 1 -> list.forEach { convertedString = it }
            }
            return convertedString.trimEnd(',')
        }

        override fun map(data: String) = data.split(DELIMITER)
    }

    companion object {
        const val DELIMITER = ","
    }
}