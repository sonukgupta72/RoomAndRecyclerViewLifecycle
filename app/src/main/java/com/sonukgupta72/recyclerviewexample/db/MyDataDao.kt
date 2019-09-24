package com.sonukgupta72.recyclerviewexample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDataDao {
    @Query("Select * From DataModel")
    fun getAllDataModel(): List<DataModel>

    @Insert
    fun addDataModel(vararg dataModel: DataModel)
}