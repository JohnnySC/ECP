package com.github.johnnysc.ecp.data.core

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ListSerializationTest {
    companion object {
        const val STRING_OF_WORDS = "cat dog pet"
        const val ONE_STRING = "cat"
    }

    private fun getList() = listOf("cat", "dog", "pet")
    private fun getOneItemList() = listOf("cat")

    private lateinit var listBase: ListSerialization

    @Before
    fun setup() {
        listBase = ListSerialization.Base()
    }

    @Test
    fun mapToList() {
        val mapToString = listBase.map(getList())
        val mapToList = listBase.map(mapToString)

        assertEquals(getList(), mapToList)
    }

    @Test
    fun mapToOneItemList() {
        val mapToString = listBase.map(getOneItemList())
        val mapToList = listBase.map(mapToString)

        assertEquals(getOneItemList(), mapToList)
    }

    @Test
    fun mapToString() {
        val mapToList = listBase.map(STRING_OF_WORDS)
        val mapToString = listBase.map(mapToList)

        assertEquals(STRING_OF_WORDS, mapToString)
    }

    @Test
    fun mapToOneString() {
        val mapToList = listBase.map(ONE_STRING)
        val mapToString = listBase.map(mapToList)

        assertEquals(ONE_STRING, mapToString)
    }
}