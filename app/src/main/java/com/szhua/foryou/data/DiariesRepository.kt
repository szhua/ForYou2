package com.szhua.foryou.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.szhua.foryou.api.BMobService
import com.szhua.foryou.dao.BMobDiaryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiariesRepository  @Inject constructor(private  val bMobService: BMobService  ) {



    fun getDiariesStream() : Flow<PagingData<BMobDiary>>{
         return  Pager(
             config = PagingConfig(enablePlaceholders = false ,pageSize = DIARY_LIMIT),
             pagingSourceFactory = { BMobDiariesPageSource(bMobService ) }
         ).flow
    }



}