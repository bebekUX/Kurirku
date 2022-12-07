package com.UGD.kurirku.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val tanggalLahir: String,
    val noTelp: String
)