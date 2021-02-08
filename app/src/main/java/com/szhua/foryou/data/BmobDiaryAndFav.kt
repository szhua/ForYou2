package com.szhua.foryou.data

import androidx.room.Embedded
import androidx.room.Relation


data class BmobDiaryAndFav(

        @Embedded val bMobDiary: BMobDiary,
        @Relation(parentColumn = "object_id",entityColumn = "object_id")
        val favoriteDiary: FavoriteDiary
)