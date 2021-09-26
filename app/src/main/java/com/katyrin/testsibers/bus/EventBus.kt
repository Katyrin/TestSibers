package com.katyrin.testsibers.bus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object EventBus {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val mutableEvent = MutableSharedFlow<Event>()
    val events = mutableEvent.asSharedFlow()

    fun invokeEvent() {
        scope.launch { mutableEvent.emit(Event) }
    }

    object Event
}