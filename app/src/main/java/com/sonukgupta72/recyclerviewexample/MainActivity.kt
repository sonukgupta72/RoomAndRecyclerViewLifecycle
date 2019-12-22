package com.sonukgupta72.recyclerviewexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonukgupta72.recyclerviewexample.db.DataModel
import com.sonukgupta72.recyclerviewexample.db.RepositoryManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryManager: RepositoryManager
    private lateinit var adapetr: AdapterClass
    private var dataModels: List<DataModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositoryManager = RepositoryManager.getRepositoryManager(applicationContext)
        repositoryManager.getLiveData().observe(this, Observer<List<DataModel>> { updateRvData(it) })
        addCountsThRxjava()
        setAddapter()
    }

    private fun updateRvData(data: List<DataModel>) {
        Toast.makeText(this, "Size: " + data.size, Toast.LENGTH_LONG).show()
        adapetr.setNotes(data)
    }

    private fun setAddapter() {
        user_list.layoutManager = LinearLayoutManager(this)
        adapetr = AdapterClass()
        user_list.adapter = adapetr
    }

    private fun addCounts() {
        repositoryManager.deleteAllTheData()
        for (i in 1..50) {
            repositoryManager.addDataItem(DataModel("Item: $i", i+20))
        }
    }

    @SuppressLint("CheckResult")
    private fun addCountsThRxjava() {
        //repositoryManager.deleteAllTheData()
        val userModels = ArrayList<DataModel>()
        for (i in 1..50) {
            userModels.add(DataModel("Item: $i", i+20))
        }
        repositoryManager.insertAllItem(userModels)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::showError)
    }

    private fun onSuccess() {
        Toast.makeText(this, "Successfully added", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("CheckResult")
    private fun setDataThroughRxJava() {
        repositoryManager.getAllData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapetr.setNotes(it)
                },
                {t->
                    print(t.message)
                }
            )
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }
}
