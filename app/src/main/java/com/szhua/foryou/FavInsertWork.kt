package com.szhua.foryou

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.data.DiariesRepository
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class FavInsertWork(context: Context,workerParams: WorkerParameters ,
                    private  val diaries:List<BMobDiary> ,
                    private  val bMobDiaryDao: BMobDiaryDao
                    ) :CoroutineWorker(context,workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
          try {
              bMobDiaryDao.insertBMobDiaryAll(diaries)
              Result.success()
          }catch (ex:Exception){
              Result.failure()
          }
    }
}