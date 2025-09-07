package com.example.mindfulmoments.data.local

import androidx.room.*
import com.example.mindfulmoments.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    
    @Query("SELECT * FROM user_profiles ORDER BY createdAt DESC LIMIT 1")
    fun getCurrentUserProfile(): Flow<UserProfile?>
    
    @Query("SELECT * FROM user_profiles WHERE id = :id")
    suspend fun getUserProfileById(id: Int): UserProfile?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile): Long
    
    @Update
    suspend fun updateUserProfile(userProfile: UserProfile)
    
    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfile)
    
    @Query("DELETE FROM user_profiles")
    suspend fun deleteAllUserProfiles()
    
    @Query("SELECT COUNT(*) FROM user_profiles")
    suspend fun getUserProfileCount(): Int
}
