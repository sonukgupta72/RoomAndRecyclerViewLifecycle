package com.sonukgupta72.recyclerviewexample.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataModel::class), version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getDataDao(): MyDataDao
}