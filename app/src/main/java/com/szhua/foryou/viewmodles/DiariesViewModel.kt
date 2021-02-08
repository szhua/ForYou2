package com.szhua.foryou.viewmodles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.data.DiariesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiariesViewModel  @Inject constructor( private val diariesRepository: DiariesRepository ,private val  diaryDao: BMobDiaryDao) :ViewModel() {

   // val diaries =  MutableLiveData<List<BMobDiary>>()

    fun findDiaries():Flow<PagingData<BMobDiary>>{
          val   diaryFlow = diariesRepository.getDiariesStream().cachedIn(viewModelScope)
          return  diaryFlow
    }


    fun insertAllDiary(dairy:BMobDiary){
        Logger.d(dairy)
        viewModelScope.launch {
            diaryDao.insertBMobDiary(dairy)
        }
    }


}