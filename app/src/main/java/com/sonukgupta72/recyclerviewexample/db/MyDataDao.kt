package com.sonukgupta72.recyclerviewexample.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface MyDataDao {
    @Query("Select * From DataModel")
    fun getAllDataModel(): Flowable<List<DataModel>>

//    @Query("Select * From DataModel Where id == :uid")
//    fun getAllDataModel(uid: Int): Maybe<List<DataModel>>

    @Insert
    fun addDataModel(vararg dataModel: DataModel)

    @Query("Delete from datamodel")
    fun deleteAllTheData()
}