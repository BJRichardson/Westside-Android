package com.digicraft.westside.service

import io.reactivex.Observable
import io.reactivex.functions.Function
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class RetryAfterTimeoutWithDelay(val maxRetries: Int, var delay: Long, val delayAmount: Long = 100) : Function<Observable<out Throwable>, Observable<*>> {
    override fun apply(attempts: Observable<out Throwable>): Observable<*> {
        return attempts.flatMap({
            if (retryCount++ < maxRetries && it is SocketTimeoutException) {
                delay += delayAmount
                Observable.timer(delay, TimeUnit.MILLISECONDS)
            } else {
                Observable.error(it)
            }
        })
    }

    private var retryCount = 0
}
