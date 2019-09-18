package com.malenalbc.stormcomics.core.exAppApptension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.malenalbc.stormcomics.presentation.BaseActivity


inline fun <reified T : ViewModel> BaseActivity.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProviders.of(this, factory).get(T::class.java)
    viewModel.body()
    return viewModel
}
