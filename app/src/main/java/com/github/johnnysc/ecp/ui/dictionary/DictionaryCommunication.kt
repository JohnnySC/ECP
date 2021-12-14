package com.github.johnnysc.ecp.ui.dictionary

import com.github.johnnysc.ecp.ui.core.Communication

interface DictionaryCommunication : Communication<List<DictionaryItemUi>> {
    class Base : Communication.Base<List<DictionaryItemUi>>(), DictionaryCommunication
}