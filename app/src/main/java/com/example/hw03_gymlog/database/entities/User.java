package com.example.hw03_gymlog.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hw03_gymlog.database.GymLogDatabase;

import java.util.Objects;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Represents a user of the GymLog application.
 * Stores username, password, admin status, and user ID.
 */
@Entity(tableName = GymLogDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Creates a new user with the given username and password.
     * Admin status is set to false by default.
     *
     * @param username the user's chosen username
     * @param password the user's chosen password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isAdmin = false;
    }

    /**
     * Checks whether this User is equal to another User.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isAdmin == user.isAdmin && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    /**
     * Generates a hash code for this User.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isAdmin);
    }

    /** Getter and setter methods below **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
