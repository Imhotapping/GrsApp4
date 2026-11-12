package com.example.grsapp4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Question(
    val id: Long,
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

class QuizDbHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE_QUESTIONS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_TEXT TEXT NOT NULL,
                $COL_OPTION_1 TEXT NOT NULL,
                $COL_OPTION_2 TEXT NOT NULL,
                $COL_OPTION_3 TEXT NOT NULL,
                $COL_OPTION_4 TEXT NOT NULL,
                $COL_CORRECT_INDEX INTEGER NOT NULL
            )
            """.trimIndent()
        )

        seedQuestions(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        onCreate(db)
    }

    fun getAllQuestions(): List<Question> {
        val result = mutableListOf<Question>()

        val cursor = readableDatabase.query(
            TABLE_QUESTIONS,
            null,
            null,
            null,
            null,
            null,
            "$COL_ID ASC"
        )

        cursor.use { c ->
            val idIndex = c.getColumnIndexOrThrow(COL_ID)
            val textIndex = c.getColumnIndexOrThrow(COL_TEXT)
            val o1 = c.getColumnIndexOrThrow(COL_OPTION_1)
            val o2 = c.getColumnIndexOrThrow(COL_OPTION_2)
            val o3 = c.getColumnIndexOrThrow(COL_OPTION_3)
            val o4 = c.getColumnIndexOrThrow(COL_OPTION_4)
            val correctIndex = c.getColumnIndexOrThrow(COL_CORRECT_INDEX)

            while (c.moveToNext()) {
                result.add(
                    Question(
                        id = c.getLong(idIndex),
                        text = c.getString(textIndex),
                        options = listOf(
                            c.getString(o1),
                            c.getString(o2),
                            c.getString(o3),
                            c.getString(o4)
                        ),
                        correctIndex = c.getInt(correctIndex)
                    )
                )
            }
        }

        return result
    }

    private fun seedQuestions(db: SQLiteDatabase) {
        insertQuestion(db, "Столица России?", "Москва", "Санкт-Петербург", "Казань", "Новосибирск", 0)
        insertQuestion(db, "Сколько будет 2 + 2?", "3", "4", "5", "6", 1)
        insertQuestion(
            db,
            "Какой язык чаще всего используется для современной Android-разработки?",
            "Kotlin", "Swift", "C#", "PHP", 0
        )
        insertQuestion(
            db,
            "Что означает SQL?",
            "Structured Query Language",
            "Simple Question Language",
            "System Query Logic",
            "Standard Quick Language",
            0
        )
        insertQuestion(
            db,
            "Какой компонент используется для навигации между фрагментами?",
            "NavHostFragment", "RecyclerView", "TextView", "SharedPreferences", 0
        )
        insertQuestion(
            db,
            "Где в Android удобно хранить небольшие настройки?",
            "SharedPreferences", "SQLite", "RecyclerView", "Toast", 0
        )
    }

    private fun insertQuestion(
        db: SQLiteDatabase,
        text: String,
        option1: String,
        option2: String,
        option3: String,
        option4: String,
        correctIndex: Int
    ) {
        val values = ContentValues().apply {
            put(COL_TEXT, text)
            put(COL_OPTION_1, option1)
            put(COL_OPTION_2, option2)
            put(COL_OPTION_3, option3)
            put(COL_OPTION_4, option4)
            put(COL_CORRECT_INDEX, correctIndex)
        }

        db.insert(TABLE_QUESTIONS, null, values)
    }

    companion object {
        private const val DB_NAME = "quiz.db"
        private const val DB_VERSION = 1
        private const val TABLE_QUESTIONS = "questions"

        private const val COL_ID = "_id"
        private const val COL_TEXT = "question_text"
        private const val COL_OPTION_1 = "option_1"
        private const val COL_OPTION_2 = "option_2"
        private const val COL_OPTION_3 = "option_3"
        private const val COL_OPTION_4 = "option_4"
        private const val COL_CORRECT_INDEX = "correct_index"
    }
}