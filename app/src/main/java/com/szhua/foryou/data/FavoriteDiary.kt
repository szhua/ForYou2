package com.szhua.foryou.data

import androidx.room.*
import java.util.*

@Entity(
    tableName = "favorite_diary",
    indices = [Index( "object_id",unique = true)],
    foreignKeys = [
        ForeignKey(entity = BMobDiary::class,parentColumns = ["object_id"],childColumns = ["object_id"])
    ]
)
data class FavoriteDiary(
    @ColumnInfo(name = "object_id") val objectId : String  ,
    @ColumnInfo(name = "fav_date") val favDate : Calendar  = Calendar.getInstance()
){


     @PrimaryKey(autoGenerate = true )
     @ColumnInfo(name = "fav_id")
     var  favId :Long  = 0

}