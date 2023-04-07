package com.sid.hassanandroidproject.dao

import androidx.room.*
import com.sid.hassanandroidproject.model.StudentReport
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM studentreport")
    fun getAllStudents(): Flow<List<StudentReport>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentReport: StudentReport)

    @Update
    suspend fun update(studentReport: StudentReport)

    @Delete
    suspend fun delete(studentReport: StudentReport)

}