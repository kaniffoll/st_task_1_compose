package com.example.domain.utilities.db

import com.example.domain.data.Album
import com.example.domain.data.Photo
import com.example.domain.data.realm.AlbumRealm
import com.example.domain.utilities.toAlbum
import com.example.domain.utilities.toAlbumRealm
import com.example.domain.utilities.toPhotoRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy

internal suspend fun Realm.saveAlbums(albums: List<Album>) = this.write {
    albums.forEach {
        copyToRealm(
            instance = it.toAlbumRealm(),
            updatePolicy = UpdatePolicy.ALL
        )
    }
}

internal fun Realm.loadAlbums() = this.query(AlbumRealm::class).find().map { it ->
    it.toAlbum()
}

internal suspend fun Realm.saveAlbumPhotos(id: Int, photos: List<Photo>) = this.write {
    val album = query(AlbumRealm::class, "id == $0", id)
        .first()
        .find() ?: return@write
    val newPhotosRealms = photos.map { photo ->
        photo.toPhotoRealm()
    }
    val toAdd = newPhotosRealms.filter { newPhoto ->
        album.photos.none { it.title == newPhoto.title && it.photo == newPhoto.photo }
    }
    album.photos.addAll(toAdd)
}

internal fun Realm.getAlbumById(id: Int) = this.query(AlbumRealm::class, "id == $0", id)
    .first()
    .find()