package com.katyrin.testsibers.bus

sealed class Event {
    data class Attack(val isChecked: Boolean) : Event()
    data class Defense(val isChecked: Boolean) : Event()
    data class HP(val isChecked: Boolean) : Event()
}
