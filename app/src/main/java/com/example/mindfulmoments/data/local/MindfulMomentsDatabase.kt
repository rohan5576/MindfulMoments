package com.example.mindfulmoments.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.mindfulmoments.data.local.converter.StringListConverter
import com.example.mindfulmoments.domain.model.MoodEntry
import com.example.mindfulmoments.domain.model.MeditationSession
import com.example.mindfulmoments.domain.model.SessionProgress
import com.example.mindfulmoments.domain.model.UserProfile
import com.example.mindfulmoments.domain.model.ProgressData
import com.example.mindfulmoments.domain.model.BreathingSession

@Database(
    entities = [
        MoodEntry::class,
        MeditationSession::class,
        SessionProgress::class,
        UserProfile::class,
        ProgressData::class,
        BreathingSession::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class MindfulMomentsDatabase : RoomDatabase() {
    
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun meditationSessionDao(): MeditationSessionDao
    abstract fun sessionProgressDao(): SessionProgressDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun progressDao(): ProgressDao
    abstract fun breathingSessionDao(): BreathingSessionDao
    
    companion object {
        @Volatile
        private var INSTANCE: MindfulMomentsDatabase? = null
        
        fun getDatabase(context: Context): MindfulMomentsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MindfulMomentsDatabase::class.java,
                    "mindful_moments_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
