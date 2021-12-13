package com.github.johnnysc.ecp.ui.dictionary

open class DictionaryUi(private val items: List<DictionaryItemUi>) {

    interface DictionaryItemUi

    class Base(items: List<DictionaryItemUi>) : DictionaryUi(items)
}