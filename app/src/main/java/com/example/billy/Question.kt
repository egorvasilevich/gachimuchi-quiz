package com.example.billy

data class Question (
    val question: String,
    val image: Int,
    val options: List<String>,
    val correctAnswer: Int
)