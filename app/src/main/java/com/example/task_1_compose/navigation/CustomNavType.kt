package com.example.task_1_compose.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.task_1_compose.data.dataclasses.Album
import com.example.task_1_compose.data.dataclasses.Post
import com.example.task_1_compose.data.dataclasses.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    
    val PostType = object : NavType<Post>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: Post) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): Post? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun serializeAsValue(value: Post): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun parseValue(value: String): Post {
            return Json.decodeFromString(Uri.decode(value))
        }
    }

    val UserType = object : NavType<User>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: User) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): User? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): User {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: User): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }

    val AlbumType = object : NavType<Album>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: Album) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): Album? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Album {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Album): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}