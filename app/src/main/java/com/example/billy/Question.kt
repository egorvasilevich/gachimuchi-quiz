package com.example.billy

data class Question (
    val question: String,
    val image: Int,
    val options: List<String>,
    // Индекс строки в массиве options + 1
    val correctAnswer: Int
)