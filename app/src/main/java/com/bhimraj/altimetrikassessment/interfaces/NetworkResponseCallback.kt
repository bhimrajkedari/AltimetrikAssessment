package com.bhimraj.altimetrikassessment.interfaces
interface NetworkResponseCallback {
    fun onNetworkSuccess()
    fun onNetworkFailure(th : Throwable)
}