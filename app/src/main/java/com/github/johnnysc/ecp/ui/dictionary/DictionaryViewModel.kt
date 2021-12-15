package com.github.johnnysc.ecp.ui.dictionary

import com.github.johnnysc.ecp.data.core.BaseViewModel
import com.github.johnnysc.ecp.data.core.SearchWord
import com.github.johnnysc.ecp.ui.core.NavigationCommunication
import com.github.johnnysc.ecp.ui.core.NavigationUi

class DictionaryViewModel(
    private val navigation: NavigationCommunication,
    dictionaryCommunication: DictionaryCommunication
) : BaseViewModel<DictionaryUi>(dictionaryCommunication), SearchWord {

    override fun searchWord(word: String) {}
}