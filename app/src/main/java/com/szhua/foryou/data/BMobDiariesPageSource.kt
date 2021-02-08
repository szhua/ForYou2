package com.szhua.foryou.data

import androidx.paging.PagingSource
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.orhanobut.logger.Logger
import com.szhua.foryou.FavInsertWork
import com.szhua.foryou.ForYouApp
import com.szhua.foryou.api.BMobService
import com.szhua.foryou.dao.BMobDiaryDao
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

private const val  DIARY_STARTING_PAGE =0
const val  DIARY_LIMIT =10

class BMobDiariesPageSource(private val service: BMobService  ) :PagingSource<Int,BMobDiary>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BMobDiary> {
        val page = params.key?: DIARY_STARTING_PAGE
        return  try {
            val response:BMobDiariesResponse =service.findDiaries(DIARY_LIMIT,page* DIARY_LIMIT,"-createdAt")
            val diaries = response.results



            LoadResult.Page(
                data = diaries,
                prevKey = if(page == DIARY_STARTING_PAGE) null else page -1 ,
                nextKey = page+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }finally {

        }
    }
}