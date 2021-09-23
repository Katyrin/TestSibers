package com.katyrin.testsibers.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(resource: Int): Unit =
    Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: String?): Unit =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()