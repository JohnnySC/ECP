package com.github.johnnysc.ecp.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.johnnysc.ecp.core.Matches
import com.github.johnnysc.ecp.core.ProvideViewModel
import com.github.johnnysc.ecp.data.core.BaseViewModel

abstract class BaseFragment<T : ViewBinding, K : BaseViewModel<*>> : Fragment(), Matches<String> {

    protected lateinit var viewModel: K
    protected var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    protected abstract fun viewModelClass(): Class<K>

    protected abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): T

    override fun matches(data: String): Boolean = name() == data
    fun name(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(viewModelClass(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}