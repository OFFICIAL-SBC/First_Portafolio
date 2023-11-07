package com.example.financesforyou.domain

import java.util.Date

data class User(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val createdAt: Date?  = null
)
