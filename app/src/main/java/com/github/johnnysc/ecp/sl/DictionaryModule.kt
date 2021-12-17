package com.github.johnnysc.ecp.sl

import com.github.johnnysc.ecp.core.CoreModule
import com.github.johnnysc.ecp.data.core.BaseModule
import com.github.johnnysc.ecp.ui.dictionary.DictionaryCommunication
import com.github.johnnysc.ecp.ui.dictionary.DictionaryViewModel

class DictionaryModule(
    private val coreModule: CoreModule,
    private val communication: DictionaryCommunication
) : BaseModule<DictionaryViewModel> {

    override fun viewModel() =
        DictionaryViewModel(coreModule.provideNavigationCommunication(), communication)
}