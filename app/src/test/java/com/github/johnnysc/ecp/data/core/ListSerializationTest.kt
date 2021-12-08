package com.github.johnnysc.ecp.data.core

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ListSerializationTest {
    private fun itemsList() = listOf("cat", "dog", "pet")
    private fun oneItemList() = listOf("cat")

    private lateinit var listBase: ListSerialization

    @Before
    fun setup() {
        listBase = ListSerialization.Base()
    }

    @Test
    fun mapToList() {
        val mapToString = listBase.map(itemsList())
        val mapToList = listBase.map(mapToString)

        assertEquals(itemsList(), mapToList)
    }

    @Test
    fun mapToOneItemList() {
        val mapToString = listBase.map(oneItemList())
        val mapToList = listBase.map(mapToString)

        assertEquals(oneItemList(), mapToList)
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

    companion object {
        const val STRING_OF_WORDS = "cat dog pet"
        const val ONE_STRING = "cat"
    }
}