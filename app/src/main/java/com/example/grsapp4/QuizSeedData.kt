package com.example.grsapp4

object QuizSeedData {

    fun questions(): List<Question> = listOf(
        Question(
            text = "Столица России?",
            option1 = "Москва",
            option2 = "Санкт-Петербург",
            option3 = "Казань",
            option4 = "Новосибирск",
            correctIndex = 0
        ),
        Question(
            text = "Сколько будет 2 + 2?",
            option1 = "3",
            option2 = "4",
            option3 = "5",
            option4 = "6",
            correctIndex = 1
        ),
        Question(
            text = "Какой язык чаще всего используется для современной Android-разработки?",
            option1 = "Kotlin",
            option2 = "Swift",
            option3 = "C#",
            option4 = "PHP",
            correctIndex = 0
        ),
        Question(
            text = "Что означает SQL?",
            option1 = "Structured Query Language",
            option2 = "Simple Question Language",
            option3 = "System Query Logic",
            option4 = "Standard Quick Language",
            correctIndex = 0
        ),
        Question(
            text = "Какой компонент используется для навигации между фрагментами?",
            option1 = "NavHostFragment",
            option2 = "RecyclerView",
            option3 = "TextView",
            option4 = "SharedPreferences",
            correctIndex = 0
        ),
        Question(
            text = "Где в Android удобно хранить небольшие настройки?",
            option1 = "SharedPreferences",
            option2 = "SQLite",
            option3 = "RecyclerView",
            option4 = "Toast",
            correctIndex = 0
        ),
        Question(
            text = "Какая библиотека из перечисленных относится к Jetpack?",
            option1 = "React",
            option2 = "Room",
            option3 = "Flask",
            option4 = "Laravel",
            correctIndex = 1
        ),
        Question(
            text = "Что делает метод findViewById()?",
            option1 = "Создаёт новую Activity",
            option2 = "Запускает фоновый поток",
            option3 = "Находит View по идентификатору",
            option4 = "Сохраняет данные в файл",
            correctIndex = 2
        )
    )
}