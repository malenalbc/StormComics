package com.malenalbc.stormcomics.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

fun androidThread(): Scheduler {
    return AndroidSchedulers.mainThread()
}
