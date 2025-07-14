package com.example.domain.db

import androidx.room.TypeConverter
import com.example.domain.data.Address
import com.example.domain.data.Comment
import com.example.domain.data.Photo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@SuppressWarnings
class CustomTypeConverters {

    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    @TypeConverter
    fun fromMutableListOfComments(value: MutableList<Comment>?): String? {
        return value?.let { json.encodeToString(ListSerializer(Comment.serializer()), it) }
    }

    @TypeConverter
    fun toMutableListOfComments(value: String?): MutableList<Comment>? {
        return value?.let {
            json.decodeFromString(ListSerializer(Comment.serializer()), it).toMutableList()
        }
    }

    @TypeConverter
    fun fromAddress(value: Address?): String? {
        return value?.let { json.encodeToString(Address.serializer(), it) }
    }

    @TypeConverter
    fun toAddress(value: String?): Address? {
        return value?.let {
            json.decodeFromString(Address.serializer(), it)
        }
    }

    @TypeConverter
    fun fromMutableListOfPhoto(value: MutableList<Photo>?): String? {
        return value?.let {
            json.encodeToString(ListSerializer(Photo.serializer()), it)
        }
    }

    @TypeConverter
    fun toMutableListOfPhoto(value: String?): MutableList<Photo>? {
        return value?.let {
            json.decodeFromString(ListSerializer(Photo.serializer()), it).toMutableList()
        }
    }
}