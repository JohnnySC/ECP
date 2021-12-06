package com.github.johnnysc.ecp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.ecp.core.ProvideViewModel

class MainActivity : AppCompatActivity(), ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun <T : ViewModel> providingViewModel(
        clazz: Class<T>,
        viewModelStoreOwner: ViewModelStoreOwner
    ): T = ViewModelProvider(viewModelStoreOwner).get(clazz)
}