package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>

    class Base : ListSerialization {
        private val delimiter = ","
        override fun map(list: List<String>): String {
            var convertedList = ""
            return if (list.size > 1) {
                for (element in list) {
                    convertedList += element + delimiter
                }
                convertedList.trimEnd(',')
            } else {
                list.forEach { convertedList = it }
                convertedList
            }
        }

        override fun map(data: String) = data.split(delimiter)
    }
}