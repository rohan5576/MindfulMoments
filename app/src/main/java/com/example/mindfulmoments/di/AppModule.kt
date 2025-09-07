// Temporarily commented out to avoid compilation errors
// Will be restored once Hilt dependencies are properly configured
/*
package com.example.mindfulmoments.di

import android.content.Context
import androidx.room.Room
import com.example.mindfulmoments.data.local.MindfulMomentsDatabase
import com.example.mindfulmoments.data.local.MoodEntryDao
import com.example.mindfulmoments.data.local.MeditationSessionDao
import com.example.mindfulmoments.data.local.SessionProgressDao
import com.example.mindfulmoments.ml.MindfulMLManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideMindfulMomentsDatabase(
        @ApplicationContext context: Context
    ): MindfulMomentsDatabase {
        return Room.databaseBuilder(
            context,
            MindfulMomentsDatabase::class.java,
            "mindful_moments_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    
    @Provides
    @Singleton
    fun provideMoodEntryDao(database: MindfulMomentsDatabase): MoodEntryDao {
        return database.moodEntryDao()
    }
    
    @Provides
    @Singleton
    fun provideMeditationSessionDao(database: MindfulMomentsDatabase): MeditationSessionDao {
        return database.meditationSessionDao()
    }
    
    @Provides
    @Singleton
    fun provideSessionProgressDao(database: MindfulMomentsDatabase): SessionProgressDao {
        return database.sessionProgressDao()
    }
    
    @Provides
    @Singleton
    fun provideMindfulMLManager(
        @ApplicationContext context: Context
    ): MindfulMLManager {
        return MindfulMLManager(context)
    }
}
*/
