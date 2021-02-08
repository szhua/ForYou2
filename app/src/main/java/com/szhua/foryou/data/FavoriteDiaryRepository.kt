package com.szhua.foryou.data

import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.dao.FavDiaryDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * 1.android dagger-hilt 注入顺序及 @HiltViewModel @AndroidEntityPoint  使用。
 * 2.android dagger-hilt AssistedFactory 创建 dagger注入对象。
 * 3.android dagger-hilt 参数引用及数据库提供全局对象使用。
 * 4.android Room 连表查询 配置。
 *
 */
@Singleton
class FavoriteDiaryRepository @Inject constructor(private val favDiaryDao: FavDiaryDao , private val diaryDao: BMobDiaryDao) {


    fun  getOneDiary(objectId: String) = diaryDao.getBMObDiary(objectId)


    fun  getFavOneDiary(objectId: String) = favDiaryDao.getOneDiary(objectId)

    suspend  fun  cancleFav(favoriteDiary: FavoriteDiary) {
            favDiaryDao.deleteFavoriteDiary(favoriteDiary)
     }

    suspend fun addFav(objectId : String ){
           val favoriteDiary =  FavoriteDiary(objectId)
           favDiaryDao.insertFavoriteDiary(favoriteDiary)
    }

    fun isFav(objectId: String) = favDiaryDao.isFavorite(objectId)



}