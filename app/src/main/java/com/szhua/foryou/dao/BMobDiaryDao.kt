package com.szhua.foryou.dao

import androidx.room.*
import com.szhua.foryou.data.BMobDiary
import kotlinx.coroutines.flow.Flow


@Dao
interface BMobDiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertBMobDiaryAll(bMobDiary: List<BMobDiary>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertBMobDiary(bMobDiary: BMobDiary)

    @Query("SELECT * FROM bmob_diary WHERE object_id = :objectId")
    fun  getBMObDiary(objectId:String): Flow<BMobDiary>

}