package com.szhua.foryou.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.orhanobut.logger.Logger
import com.szhua.foryou.dao.BMobDiaryDao
import com.szhua.foryou.dao.FavDiaryDao

@Database(entities = [FavoriteDiary::class,BMobDiary::class],version = 4,exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun  favDiaryDao(): FavDiaryDao
    abstract  fun  bmobDiaryDao():BMobDiaryDao

    val  MIGRATION_3_4 = object : Migration(2,3){
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }


    companion object{

        @Volatile private var  instance: AppDatabase?=null

        fun getInstance(context:Context) : AppDatabase {
            return  instance ?: synchronized(this){
                 instance?: buildDataBase(context).also {
                      instance =it
                 }
            }
        }

        private fun buildDataBase(context: Context) :AppDatabase {
            return  Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "for-you-db"
            ).addCallback(object :Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Logger.d("DB is Created ")
                }
            }).fallbackToDestructiveMigration().
            build()
        }

    }




}