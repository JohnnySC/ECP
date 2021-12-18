package com.github.johnnysc.ecp.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.johnnysc.ecp.data.core.DependencyContainer

class ViewModelsFactory(private val dependencyContainer: DependencyContainer) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        dependencyContainer.module(modelClass).viewModel() as T
}