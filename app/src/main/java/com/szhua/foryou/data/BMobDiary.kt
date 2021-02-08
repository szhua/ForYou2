package com.szhua.foryou.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable


@Entity(tableName = "bmob_diary", )
data class BMobDiary(

    @ColumnInfo(name="content")
    val content :String,
    @ColumnInfo(name="create_at")
    val createdAt : String ,

    @ColumnInfo(name="name",defaultValue = "NULL")
    @Nullable
    var  name :String ?="" ,

    @PrimaryKey
    @ColumnInfo(name="object_id")
    val objectId :String  ,

    @ColumnInfo(name="update_at")
    val updatedAt :String ,

    @ColumnInfo(name="dary_img" ,defaultValue = "NULL")
    @Nullable
    @field:SerializedName("dary_img") var  diaryImg :String ?=""

)
