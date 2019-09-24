package com.sonukgupta72.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonukgupta72.recyclerviewexample.db.DataModel
import com.sonukgupta72.recyclerviewexample.db.RepositoryManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryManager: RepositoryManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCounts()

        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = AdapterClass(repositoryManager.getAll())
    }

    private fun addCounts() {
        repositoryManager = RepositoryManager.getRepositoryManager(applicationContext)
        for (i in 1..50) {
            repositoryManager.add(DataModel("Item: $i", i+20))
        }
    }
}
