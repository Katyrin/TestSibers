package com.katyrin.testsibers.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.katyrin.testsibers.R
import com.katyrin.testsibers.bus.Event
import com.katyrin.testsibers.bus.EventBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.attack_check -> {
                item.isChecked = !item.isChecked
                EventBus.invokeEvent(Event.Attack(item.isChecked))
                true
            }
            R.id.defense_check -> {
                item.isChecked = !item.isChecked
                EventBus.invokeEvent(Event.Defense(item.isChecked))
                true
            }
            R.id.hp_check -> {
                item.isChecked = !item.isChecked
                EventBus.invokeEvent(Event.HP(item.isChecked))
                true
            }
            else -> false
        }

}