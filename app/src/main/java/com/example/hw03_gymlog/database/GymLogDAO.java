package com.example.hw03_gymlog.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw03_gymlog.database.entities.GymLog;
import java.util.List;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Data Access Object (DAO) for the GymLog table.
 * Defines how gym log records are inserted and queried from the database.
 */
@Dao
public interface GymLogDAO {
    /**
     * Inserts a new GymLog into the database.
     * If a record with the same ID already exists, it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GymLog gymLog);

    /**
     * Returns all gym log records in the database,
     * ordered by date from newest to oldest.
     */
    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " ORDER BY date DESC")
    List<GymLog> getAllRecords();

    /**
     * Returns all gym logs for a specific user,
     * ordered by date from newest to oldest.
     */

    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " WHERE userId = :loggedInUserId ORDER BY date DESC")
    List<GymLog> getRecordsByUserId(int loggedInUserId);

    /**
     * Returns a LiveData list of logs for a specific user.
     * LiveData allows the UI to update automatically when data changes.
     */
    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " WHERE userId = :loggedInUserId ORDER BY date DESC")
    LiveData<List<GymLog>> getRecordsByUserIdLiveData(int loggedInUserId);
}
