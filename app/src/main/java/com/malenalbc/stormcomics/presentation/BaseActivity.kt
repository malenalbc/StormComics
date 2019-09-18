package com.malenalbc.stormcomics.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.malenalbc.stormcomics.App
import com.malenalbc.stormcomics.di.ApplicationComponent
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (this.application as App).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}
