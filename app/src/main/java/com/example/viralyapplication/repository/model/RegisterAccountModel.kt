package com.example.viralyapplication.repository.model

data class RegisterAccountModel(
        val email: String,
        val username: String,
        val display_name: String,
        val password: String
)