package com.github.johnnysc.ecp.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.sl.ProvideViewModel
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.main.nav_screen.BaseFragmentFactory

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var fragmentFactory: FragmentFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentFactory = BaseFragmentFactory(R.id.container, supportFragmentManager)
        viewModel = provideViewModel(MainViewModel::class.java, this)
        viewModel.observe(this) { navScreen ->
            fragmentFactory.fragment(navigationScreen = navScreen)
        }


    }

    override fun <T : androidx.lifecycle.ViewModel> provideViewModel(
        clazz: Class<T>,
        owner: ViewModelStoreOwner
    ) = (application as ProvideViewModel).provideViewModel(clazz, this)

}