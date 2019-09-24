package com.sonukgupta72.recyclerviewexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataModel(
    @ColumnInfo(name = "fullName") val name: String?,
    @ColumnInfo(name = "age") val age: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}