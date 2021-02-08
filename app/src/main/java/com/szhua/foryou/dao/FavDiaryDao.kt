package com.szhua.foryou.dao

import androidx.room.*
import com.szhua.foryou.data.BmobDiaryAndFav
import com.szhua.foryou.data.FavoriteDiary
import kotlinx.coroutines.flow.Flow

@Dao
interface  FavDiaryDao {

    /**
     * 查找当前喜欢收藏的
     */
    @Transaction
    @Query("SELECT * FROM bmob_diary as b WHERE b.object_id in (SELECT object_id FROM favorite_diary as f WHERE f.object_id = :objectId) ")
    fun getOneDiary(objectId: String) : Flow<BmobDiaryAndFav>




    @Transaction
    @Query("SELECT * FROM bmob_diary as d WHERE d.object_id in (SELECT object_id FROM favorite_diary as fd )")
    fun findFavoriteDiariesWithDiaryDetail() : Flow<List<BmobDiaryAndFav>>

    /**
     * 查看是否收藏了；
     */
    @Query("SELECT EXISTS( SELECT * FROM favorite_diary WHERE object_id = :objectId LIMIT 1)")
    fun isFavorite(objectId :String ) :Flow<Boolean>


    /**
     * 插入一个喜欢
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertFavoriteDiary(favoriteDiary: FavoriteDiary):Long


    /**
     * 删除一个喜欢；
     */
    @Delete
    suspend fun  deleteFavoriteDiary(favoriteDiary:FavoriteDiary)





}