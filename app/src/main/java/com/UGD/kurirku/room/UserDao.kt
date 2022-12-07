package com.UGD.kurirku.room

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getUsers() : List<User>

    @Query("SELECT * FROM User WHERE id=:user_id")
    fun getUser(user_id: Int) : List<User>
}