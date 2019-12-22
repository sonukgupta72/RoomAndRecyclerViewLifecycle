package com.sonukgupta72.recyclerviewexample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MyDataDao {
    @Query("Select * From DataModel")
    fun getAllDataModel(): Flowable<List<DataModel>>

//    @Query("Select * From DataModel Where id == :uid")
//    fun getAllDataModel(uid: Int): Maybe<List<DataModel>>

    @Insert
    fun addDataModel(vararg dataModel: DataModel)


    @Insert
    fun insertDataModel(dataModels: ArrayList<DataModel>): Completable

    @Query("Delete from datamodel")
    fun deleteAllTheData()
}