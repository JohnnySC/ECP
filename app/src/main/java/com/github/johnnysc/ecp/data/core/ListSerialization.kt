package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>

    class Base : ListSerialization {

        override fun map(list: List<String>): String {
            val convertedString = StringBuilder()
            when (list.isNotEmpty()) {
                list.size > 1 -> list.forEach { convertedString.append(it + DELIMITER) }
                list.size == 1 -> list.forEach { convertedString.append(it) }
            }
            return convertedString.trimEnd(',').toString()
        }

        override fun map(data: String) = data.split(DELIMITER)
    }

    companion object {
        const val DELIMITER = ","
    }
}