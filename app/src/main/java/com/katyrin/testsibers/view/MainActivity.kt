package com.katyrin.testsibers.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.katyrin.testsibers.R
import com.katyrin.testsibers.bus.EventBus
import com.katyrin.testsibers.model.storage.prefs.Storage
import com.katyrin.testsibers.model.storage.prefs.StorageImpl

class MainActivity : AppCompatActivity() {

    private val storage: Storage by lazy { StorageImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        initItems(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initItems(menu: Menu?) {
        menu?.findItem(R.id.attack_check)?.isChecked = storage.getAttackIsCheck()
        menu?.findItem(R.id.defense_check)?.isChecked = storage.getDefenseIsCheck()
        menu?.findItem(R.id.hp_check)?.isChecked = storage.getHPIsCheck()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.attack_check -> {
                item.isChecked = !item.isChecked
                storage.setAttackIsCheck(item.isChecked)
                EventBus.invokeEvent()
                true
            }
            R.id.defense_check -> {
                item.isChecked = !item.isChecked
                storage.setDefenseIsCheck(item.isChecked)
                EventBus.invokeEvent()
                true
            }
            R.id.hp_check -> {
                item.isChecked = !item.isChecked
                storage.setHPIsCheck(item.isChecked)
                EventBus.invokeEvent()
                true
            }
            else -> false
        }
}