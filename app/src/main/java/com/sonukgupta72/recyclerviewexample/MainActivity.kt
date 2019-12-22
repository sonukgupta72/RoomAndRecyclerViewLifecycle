package com.sonukgupta72.recyclerviewexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonukgupta72.recyclerviewexample.db.DataModel
import com.sonukgupta72.recyclerviewexample.db.RepositoryManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryManager: RepositoryManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCountsThRxjava()
    }

    private fun addCounts() {
        repositoryManager = RepositoryManager.getRepositoryManager(applicationContext)
        //repositoryManager.deleteAllTheData()
        for (i in 1..50) {
            repositoryManager.addDataItem(DataModel("Item: $i", i+20))
        }
    }

    @SuppressLint("CheckResult")
    private fun addCountsThRxjava() {
        repositoryManager = RepositoryManager.getRepositoryManager(applicationContext)
        repositoryManager.deleteAllTheData()
        val userModels = ArrayList<DataModel>()
        for (i in 1..50) {
            userModels.add(DataModel("Item: $i", i+20))
        }
        repositoryManager.insertAllItem(userModels)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::showError)
    }

    @SuppressLint("CheckResult")
    private fun onSuccess() {
        Toast.makeText(this, "Successfully added", Toast.LENGTH_LONG).show()
        repositoryManager.getAllData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    user_list.layoutManager = LinearLayoutManager(this)
                    user_list.adapter = AdapterClass(it)
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
