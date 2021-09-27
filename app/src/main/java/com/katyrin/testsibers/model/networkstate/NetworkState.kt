package com.katyrin.testsibers.model.networkstate

interface NetworkState {
    //Checking network status, true - network available, false - network not available
    fun isOnline(): Boolean
}