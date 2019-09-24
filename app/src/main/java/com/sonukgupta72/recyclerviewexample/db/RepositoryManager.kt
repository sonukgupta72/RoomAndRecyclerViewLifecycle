package com.sonukgupta72.recyclerviewexample.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room

class RepositoryManager {

    companion object {
        lateinit var myDataBase: MyDataBase

        fun getRepositoryManager(context: Context) : RepositoryManager {
            myDataBase = getDataBase(context)
            return RepositoryManager()
        }

        private fun getDataBase(context: Context): MyDataBase {
            if (::myDataBase.isInitialized && myDataBase != null) {
                return myDataBase
            } else{
                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, "my-table")
                    .fallbackToDestructiveMigration().build()
                return myDataBase
            }
        }
    }

    fun getAll(): List<DataModel> {
        return GetModelAsyncTask().execute().get()
    }

    fun add(dataModel: DataModel) {
        AddDatatModel(dataModel).execute()
    }

    private class GetModelAsyncTask: AsyncTask<Void, Void, List<DataModel>>() {

        override fun doInBackground(vararg voids: Void): List<DataModel> {
            return myDataBase.getDataDao().getAllDataModel()
        }
    }
    private class AddDatatModel internal constructor(private val dataModel: DataModel) :
        AsyncTask<Int, Void, Boolean>() {
        override fun doInBackground(vararg p0: Int?): Boolean {
            myDataBase.getDataDao().addDataModel(dataModel)
            return true
        }
    }
}