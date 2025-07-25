package com.example.domain.utilities

import android.annotation.SuppressLint
import android.content.Context


//TODO: не уверен что тут все норм
@SuppressLint("StaticFieldLeak")
object ResourceProvider {
    lateinit var context: Context
    fun getStringResource(resId: Int): String = context.getString(resId)
}