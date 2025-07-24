package com.example.domain.utilities

import android.content.Context

object ResourceProvider {
    fun getStringResource(resId: Int, context: Context): String = context.getString(resId)
}