package com.github.johnnysc.ecp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.core.ProvideViewModel

class MainActivity : AppCompatActivity(), ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun <T : ViewModel> provideViewModel(
        clazz: Class<T>,
        viewModelStoreOwner: ViewModelStoreOwner
    ): T = (application as ProvideViewModel).provideViewModel(clazz, viewModelStoreOwner)
}