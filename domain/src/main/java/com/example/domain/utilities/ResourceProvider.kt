package com.example.domain.utilities

import android.content.Context

class ResourceProvider(private val context: Context) {
    fun getStringResource(resId: Int): String = context.getString(resId)
}