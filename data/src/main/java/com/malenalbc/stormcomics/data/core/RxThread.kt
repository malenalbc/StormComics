package com.malenalbc.stormcomics.data.core

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


fun ioThread(): Scheduler {
    return Schedulers.io()
}

fun newThread():Scheduler{
    return Schedulers.newThread()
}
