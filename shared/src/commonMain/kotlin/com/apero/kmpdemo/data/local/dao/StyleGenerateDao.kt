package com.apero.kmpdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.apero.kmpdemo.data.local.entity.StyleItemEntity

@Dao
interface StyleGenerateDao {

    @Query("SELECT * FROM style_item_table")
    suspend fun getAll(): List<StyleItemEntity>

    @Query("SELECT * FROM style_item_table WHERE id = :id LIMIT 1")
    suspend fun getFirstByStyleId(id: Long): StyleItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(style: StyleItemEntity) : Long

    @Update
    suspend fun update(style: StyleItemEntity)

    @Delete
    suspend fun delete(style: StyleItemEntity)

    @Query("DELETE FROM style_item_table")
    suspend fun deleteAll()
}