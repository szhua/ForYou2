package com.szhua.foryou.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.data.FavoriteDiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryItemViewModel  @Inject constructor(val diaryDao: BMobDiaryDao) :ViewModel() {


    fun  insertDiary(diary: BMobDiary){
          viewModelScope.launch {
              diaryDao.insertBMobDiary(diary)
          }
    }


}