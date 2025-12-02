package com.example.hw03_gymlog.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw03_gymlog.database.entities.User;

import java.util.List;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * DAO interface for accessing User data in the database.
 * Provides methods for inserting, deleting, and querying users.
 */
@Dao
public interface UserDAO {

    /**
     * Retrieves a user based on their username.
     *
     * @param username the username to search for
     * @return LiveData wrapping the User object
     */
    @Query("SELECT * from " + GymLogDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    /**
     * Retrieves a user based on their user ID.
     *
     * @param userId the unique ID of the user
     * @return LiveData wrapping the User object
     */
    @Query("SELECT * from " + GymLogDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

    /**
     * Inserts one or more users into the database.
     * If a user already exists, it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    /**
     * Deletes a single user from the database.
     *
     * @param user the user to delete
     */
    @Delete
    void delete(User user);

    /**
     * Returns a list of all users ordered alphabetically by username.
     *
     * @return LiveData list of User objects
     */
    @Query("SELECT * FROM " + GymLogDatabase.USER_TABLE + " ORDER BY username")
    LiveData<List<User>> getAllUsers();

    /**
     * Deletes all users from the table.
     */
    @Query("DELETE from " + GymLogDatabase.USER_TABLE)
    void deleteAll();
}
