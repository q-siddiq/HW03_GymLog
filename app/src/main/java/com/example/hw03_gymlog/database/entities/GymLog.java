package com.example.hw03_gymlog.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hw03_gymlog.database.GymLogDatabase;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Represents a single gym log entry.
 * Each log stores an exercise name, weight, reps, date, and the user who created it.
 */
@Entity(tableName = GymLogDatabase.GYM_LOG_TABLE)
public class GymLog {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exercise;
    private double weight;
    private int reps;
    private LocalDateTime date;
    private int userId;

    /**
     * Creates a new GymLog entry for a user.
     * Automatically sets the log date to the current time.
     *
     * @param exercise the name of the exercise
     * @param weight   the weight used
     * @param reps     number of repetitions
     * @param userId   the ID of the user who created the log
     */
    public GymLog(String exercise, double weight, int reps, int userId) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.userId = userId;
        date = LocalDateTime.now();
    }

    /**
     * Returns a formatted string of the log details.
     */
    @NonNull
    @Override
    public String toString() {
        return exercise + '\n' +
                "weight:" + weight + '\n' +
                "reps:" + reps + '\n' +
                "date:" + date.toString() + '\n' +
                "=-=-=-=-=-=-=\n";
    }

    /**
     * Checks if this log is equal to another log.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GymLog gymLog = (GymLog) o;
        return id == gymLog.id && Double.compare(weight, gymLog.weight) == 0 && reps == gymLog.reps && userId == gymLog.userId && Objects.equals(exercise, gymLog.exercise) && Objects.equals(date, gymLog.date);
    }

    /**
     * Generates a hash code for this log.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, exercise, weight, reps, date, userId);
    }

    /** Getter and setter methods below **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
