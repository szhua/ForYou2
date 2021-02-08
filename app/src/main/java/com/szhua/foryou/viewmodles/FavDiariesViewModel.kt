package com.szhua.foryou.viewmodles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.szhua.foryou.dao.FavDiaryDao
import com.szhua.foryou.data.BMobDiary
import com.szhua.foryou.data.FavoriteDiary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavDiariesViewModel @Inject constructor(private val favDiaryDao: FavDiaryDao ) : ViewModel() {

     val favDiaries = favDiaryDao.findFavoriteDiariesWithDiaryDetail().asLiveData()


     fun deleteOne(favoriteDiary: FavoriteDiary){
           viewModelScope.launch {
                favDiaryDao.deleteFavoriteDiary(favoriteDiary)
           }
     }
}