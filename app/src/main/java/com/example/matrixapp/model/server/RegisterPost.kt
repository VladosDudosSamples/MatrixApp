package com.example.matrixapp.model.server

data class RegisterPost(
    val login: String,
    val password: String,
    val password_confirm: String
)