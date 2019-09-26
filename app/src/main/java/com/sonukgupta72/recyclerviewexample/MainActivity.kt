package com.sonukgupta72.recyclerviewexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonukgupta72.recyclerviewexample.db.DataModel
import com.sonukgupta72.recyclerviewexample.db.RepositoryManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryManager: RepositoryManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCounts()

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

    private fun addCounts() {
        repositoryManager = RepositoryManager.getRepositoryManager(applicationContext)
        repositoryManager.deleteAllTheData()
        for (i in 1..50) {
            repositoryManager.addDataItem(DataModel("Item: $i", i+20))
        }
    }
}
