package com.github.johnnysc.ecp.ui.dictionary

import com.github.johnnysc.ecp.data.core.BaseViewModel
import com.github.johnnysc.ecp.data.core.SearchWord
import com.github.johnnysc.ecp.ui.core.NavigationCommunication

class DictionaryViewModel(
    navigation: NavigationCommunication,
    dictionaryCommunication: DictionaryCommunication
) : BaseViewModel<DictionaryUi>(navigation, dictionaryCommunication), SearchWord {

    override fun searchWord(word: String) {}
}