package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.ecp.sl.ProvideSharedPreferences

class ProvideCitySharedPref(coreModule: CoreModule):
    ProvideSharedPreferences.AbstractProvideSharedPreferences(coreModule)
{
    override val sharedPreferencesName="citySharedPref"
}