package com.katyrin.testsibers.model.storage.prefs

import android.content.Context
import android.content.SharedPreferences
import com.katyrin.testsibers.R

class StorageImpl(context: Context) : Storage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    override fun getAttackIsCheck(): Boolean = prefs.getBoolean(IS_ATTACK, false)

    override fun setAttackIsCheck(isCheck: Boolean): Unit =
        prefs.edit().apply { putBoolean(IS_ATTACK, isCheck) }.apply()

    override fun getDefenseIsCheck(): Boolean = prefs.getBoolean(IS_DEFENSE, false)

    override fun setDefenseIsCheck(isCheck: Boolean): Unit =
        prefs.edit().apply { putBoolean(IS_DEFENSE, isCheck) }.apply()

    override fun getHPIsCheck(): Boolean = prefs.getBoolean(IS_HP, false)

    override fun setHPIsCheck(isCheck: Boolean): Unit =
        prefs.edit().apply { putBoolean(IS_HP, isCheck) }.apply()

    override fun isChecked(): Boolean = when (true) {
        prefs.getBoolean(IS_ATTACK, false) -> true
        prefs.getBoolean(IS_DEFENSE, false) -> true
        prefs.getBoolean(IS_HP, false) -> true
        else -> false
    }

    private companion object {
        const val IS_ATTACK = "IS_ATTACK"
        const val IS_DEFENSE = "IS_DEFENSE"
        const val IS_HP = "IS_HP"
    }
}