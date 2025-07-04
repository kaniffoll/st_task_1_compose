package com.example.domain.db.daos.base

import androidx.room.Update
import androidx.room.Upsert

interface BaseDao<T> {
    @Upsert
    suspend fun upsertAll(objs: List<T>)

    @Update
    suspend fun update(obj: T)
}