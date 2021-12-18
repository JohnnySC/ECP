package com.github.johnnysc.ecp.data.core

import org.junit.Assert.*
import org.junit.Test

class ListSerializationTest {

    private val listBase = ListSerialization.Base()

    @Test
    fun testWithEmptyString() {
        val list = listBase.map("")
        val actual = listBase.map(list)
        val expected = ""

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringOneWord() {
        val list = listBase.map("one")
        val actual = listBase.map(list)
        val expected = "one"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringTwoWordsNoSeparators() {
        val list = listBase.map("one two")
        val actual = listBase.map(list)
        val expected = "one two"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringTwoWordsWithSeparator() {
        val list = listBase.map("one, two")
        val actual = listBase.map(list)
        val expected = "one, two"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithSeparatorInTheBeginning() {
        val list = listBase.map(", one, two")
        val actual = listBase.map(list)
        val expected = ", one, two"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithCommaInTheBeginning() {
        val list = listBase.map(",one, two")
        val actual = listBase.map(list)
        val expected = ",one, two"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithTheSeparatorInTheEnd() {
        val list = listBase.map("one, two, ")
        val actual = listBase.map(list)
        val expected = "one, two, "

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithTheCommaInTheEnd() {
        val list = listBase.map("one, two,")
        val actual = listBase.map(list)
        val expected = "one, two,"

        assertEquals(expected, actual)
    }

    @Test
    fun testWithEmptyList() {
        val string = listBase.map(listOf())
        val actual = listBase.map(string)
        val expected = listOf("")
        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithEmptyString() {
        val string = listBase.map(listOf(" "))
        val actual = listBase.map(string)
        val expected = listOf(" ")

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithOneString() {
        val string = listBase.map(listOf("one"))
        val actual = listBase.map(string)
        val expected = listOf("one")

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithTwoStrings() {
        val string = listBase.map(listOf("one", "two"))
        val actual = listBase.map(string)
        val expected = listOf("one", "two")

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithThreeStrings() {
        val string = listBase.map(listOf("one", "two", "three"))
        val actual = listBase.map(string)
        val expected = listOf("one", "two", "three")

        assertEquals(expected, actual)
    }
}