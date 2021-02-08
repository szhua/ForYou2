package com.szhua.foryou.viewmodles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.szhua.foryou.data.FavoriteDiaryRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DiaryDetailViewModel  @AssistedInject constructor(
       private val  favoriteDiaryRepository: FavoriteDiaryRepository ,
       @Assisted private val  objectId :String
) :ViewModel() {

    val isFav = favoriteDiaryRepository.isFav(objectId).asLiveData()
    val diary = favoriteDiaryRepository.getOneDiary(objectId).asLiveData()
    val fav = favoriteDiaryRepository.getFavOneDiary(objectId).asLiveData()


    fun addFav(){
        viewModelScope.launch {
            favoriteDiaryRepository.addFav(objectId)
        }
    }
    companion object{
        fun provideFactory(
                assistedFactory: DiaryDetailViewModelFactory,
                objectId: String
        ):ViewModelProvider.Factory = object  :ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
              return   assistedFactory.create(objectId) as T
            }
        }
    }

}

@AssistedFactory
interface  DiaryDetailViewModelFactory{
     fun   create(objectId: String) : DiaryDetailViewModel
}