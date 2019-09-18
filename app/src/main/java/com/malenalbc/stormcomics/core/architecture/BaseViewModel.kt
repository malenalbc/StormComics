package com.malenalbc.stormcomics.core.architecture

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }
}
