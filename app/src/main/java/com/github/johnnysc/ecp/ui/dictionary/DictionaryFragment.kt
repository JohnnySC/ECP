package com.github.johnnysc.ecp.ui.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.core.ProvideViewModel
import com.github.johnnysc.ecp.databinding.FragmentDictionaryBinding
import com.github.johnnysc.ecp.ui.core.BaseFragment

class DictionaryFragment : BaseFragment<FragmentDictionaryBinding, DictionaryViewModel>() {

    override fun viewModelClass(): Class<DictionaryViewModel> = DictionaryViewModel::class.java

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDictionaryBinding {

        return FragmentDictionaryBinding.inflate(inflater, container, false)

    }

}