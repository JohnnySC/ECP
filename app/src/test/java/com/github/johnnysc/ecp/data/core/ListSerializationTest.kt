package com.github.johnnysc.ecp.data.core

import org.junit.Assert.*
import org.junit.Test

class ListSerializationTest {

    private val listBase = ListSerialization.Base()

    private val emptyString = ""
    private val stringOneWord = "one"
    private val stringTwoWordsNoSeparators = "one two"
    private val stringTwoWordsWithSeparator = "one, two"
    private val stringWithSeparatorInTheBeginning = ", one, two"
    private val stringWithCommaInTheBeginning = ",one, two"
    private val stringWithTheSeparatorInTheEnd = "one, two, "
    private val stringWithTheCommaInTheEnd = "one, two,"

    private val emptyList = listOf<String>()
    private val listWithEmptyString = listOf("")
    private val listWithOneString = listOf("one")
    private val listWithTwoStrings = listOf("one", "two")
    private val listWithThreeStrings = listOf("one", "two", "three")

    @Test
    fun testWithEmptyString (){
        val list = listBase.map(emptyString)
        val actual = listBase.map(list)
        val expected = emptyString

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringOneWord (){
        val list = listBase.map(stringOneWord)
        val actual = listBase.map(list)
        val expected = stringOneWord

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringTwoWordsNoSeparators (){
        val list = listBase.map(stringTwoWordsNoSeparators)
        val actual = listBase.map(list)
        val expected = stringTwoWordsNoSeparators

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringTwoWordsWithSeparator (){
        val list = listBase.map(stringTwoWordsWithSeparator)
        val actual = listBase.map(list)
        val expected = stringTwoWordsWithSeparator

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithSeparatorInTheBeginning (){
        val list = listBase.map(stringWithSeparatorInTheBeginning)
        val actual = listBase.map(list)
        val expected = stringWithSeparatorInTheBeginning

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithCommaInTheBeginning (){
        val list = listBase.map(stringWithCommaInTheBeginning)
        val actual = listBase.map(list)
        val expected = stringWithCommaInTheBeginning

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithTheSeparatorInTheEnd (){
        val list = listBase.map(stringWithTheSeparatorInTheEnd)
        val actual = listBase.map(list)
        val expected = stringWithTheSeparatorInTheEnd

        assertEquals(expected, actual)
    }

    @Test
    fun testWithStringWithTheCommaInTheEnd (){
        val list = listBase.map(stringWithTheCommaInTheEnd)
        val actual = listBase.map(list)
        val expected = stringWithTheCommaInTheEnd

        assertEquals(expected, actual)
    }

    @Test
    fun testWithEmptyList (){
        val string = listBase.map(emptyList)
        val actual = listBase.map(string)
        val expected = listWithEmptyString // Ожидается список из одной пустой стринги

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithEmptyString (){
        val string = listBase.map(listWithEmptyString)
        val actual = listBase.map(string)
        val expected = listWithEmptyString

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithOneString (){
        val string = listBase.map(listWithOneString)
        val actual = listBase.map(string)
        val expected = listWithOneString

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithTwoStrings (){
        val string = listBase.map(listWithTwoStrings)
        val actual = listBase.map(string)
        val expected = listWithTwoStrings

        assertEquals(expected, actual)
    }

    @Test
    fun testWithListWithThreeStrings (){
        val string = listBase.map(listWithThreeStrings)
        val actual = listBase.map(string)
        val expected = listWithThreeStrings

        assertEquals(expected, actual)
    }
}