package com.example.domain.db

import androidx.room.TypeConverters
import com.example.domain.data.dataclasses.Address
import com.example.domain.data.dataclasses.Comment
import com.example.domain.data.dataclasses.Photo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class TypeConverters {

    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    @TypeConverters
    fun fromMutableListOfComments(value: MutableList<Comment>?): String? {
        return value?.let { json.encodeToString(ListSerializer(Comment.serializer()), it) }
    }

    @TypeConverters
    fun toMutableListOfComments(value: String?): MutableList<Comment>? {
        return value?.let {
            json.decodeFromString(ListSerializer(Comment.serializer()), it).toMutableList()
        }
    }

    @TypeConverters
    fun fromAddress(value: Address?): String? {
        return value?.let { json.encodeToString(Address.serializer(), it) }
    }

    @TypeConverters
    fun toAddress(value: String?): Address? {
        return value?.let {
            json.decodeFromString(Address.serializer(), it)
        }
    }

    @TypeConverters
    fun fromMutableListOfPhoto(value: MutableList<Photo>?): String? {
        return value?.let {
            json.encodeToString(ListSerializer(Photo.serializer()), it)
        }
    }

    @TypeConverters
    fun toMutableListOfPhoto(value: String?): MutableList<Photo>? {
        return value?.let {
            json.decodeFromString(ListSerializer(Photo.serializer()), it).toMutableList()
        }
    }
}