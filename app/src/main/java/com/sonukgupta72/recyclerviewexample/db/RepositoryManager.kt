package com.sonukgupta72.recyclerviewexample.db

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RepositoryManager {

    companion object {
        lateinit var myDataBase: MyDataBase

        fun getRepositoryManager(context: Context) : RepositoryManager {
            myDataBase = getDataBase(context)
            return RepositoryManager()
        }

        private fun getDataBase(context: Context): MyDataBase {
            if (::myDataBase.isInitialized) {
                return myDataBase
            } else{
                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, "my-table")
                    .fallbackToDestructiveMigration().build()
                return myDataBase
            }
        }
    }

    fun addDataItem(dataModel: DataModel) {
        AddDatatModel(dataModel).execute()
    }

    fun getAllData(): Flowable<List<DataModel>> {
        return myDataBase.getDataDao().getAllDataModel()
    }

    fun deleteAllTheData(){
        DeleteAllTheData().execute()
    }


    @SuppressLint("CheckResult")
    fun addDataItemThroughRxJava(dataModel: DataModel) {
        Completable.fromAction {
            myDataBase.getDataDao().addDataModel(dataModel)
        }
    }

//    private fun getDataItems() {
//        return GetModelAsyncTask().execute().get()
//    }
//
//    private class GetModelAsyncTask: AsyncTask<Void, Void, List<DataModel>>() {
//
//        override fun doInBackground(vararg voids: Void): List<DataModel> {
//            return myDataBase.getDataDao().getAllDataModel()
//        }
//    }
    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private class AddDatatModel internal constructor(private val dataModel: DataModel) :
        AsyncTask<Int, Void, Boolean>() {
        override fun doInBackground(vararg p0: Int?): Boolean {
            myDataBase.getDataDao().addDataModel(dataModel)
            return true
        }
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private class DeleteAllTheData internal constructor() :
        AsyncTask<Int, Void, Boolean>() {
        override fun doInBackground(vararg p0: Int?): Boolean {
            myDataBase.getDataDao().deleteAllTheData()
            return true
        }
    }
}