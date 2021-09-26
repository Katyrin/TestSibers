package com.katyrin.testsibers.model.storage.prefs

interface Storage {
    fun getAttackIsCheck(): Boolean
    fun setAttackIsCheck(isCheck: Boolean)
    fun getDefenseIsCheck(): Boolean
    fun setDefenseIsCheck(isCheck: Boolean)
    fun getHPIsCheck(): Boolean
    fun setHPIsCheck(isCheck: Boolean)
    fun isChecked(): Boolean
}