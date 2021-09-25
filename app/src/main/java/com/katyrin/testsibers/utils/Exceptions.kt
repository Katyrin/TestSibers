package com.katyrin.testsibers.utils

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

fun Fragment.toast(resource: Int): Unit =
    Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: String?): Unit =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Activity.toast(message: String?): Unit =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun AppCompatImageView.loadPokemonImage(imageUrl: String?): Unit =
    Picasso.get().load(imageUrl).into(this)