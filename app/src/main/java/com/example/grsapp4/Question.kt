package com.example.grsapp4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "question_text") val text: String,
    @ColumnInfo(name = "option_1") val option1: String,
    @ColumnInfo(name = "option_2") val option2: String,
    @ColumnInfo(name = "option_3") val option3: String,
    @ColumnInfo(name = "option_4") val option4: String,
    @ColumnInfo(name = "correct_index") val correctIndex: Int
) {
    val options: List<String>
        get() = listOf(option1, option2, option3, option4)
}