package com.example.domain.di

import com.example.domain.data.realm.AddressRealm
import com.example.domain.data.realm.AlbumRealm
import com.example.domain.data.realm.CommentRealm
import com.example.domain.data.realm.PhotoRealm
import com.example.domain.data.realm.PostRealm
import com.example.domain.data.realm.TodoRealm
import com.example.domain.data.realm.UserRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val databaseModule = module {
    single {
        Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    AddressRealm::class,
                    AlbumRealm::class,
                    CommentRealm::class,
                    PhotoRealm::class,
                    PostRealm::class,
                    TodoRealm::class,
                    UserRealm::class,
                )
            )
        )
    }
}