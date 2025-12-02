package com.example.hw03_gymlog.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.hw03_gymlog.database.entities.GymLog;
import com.example.hw03_gymlog.MainActivity;
import com.example.hw03_gymlog.database.entities.User;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Repository class that provides a clean API for accessing GymLog and User data.
 * Handles database operations on background threads.
 */
public class GymLogRepository {
    private final GymLogDAO gymLogDAO;
    private final UserDAO userDAO;
    private ArrayList<GymLog> allLogs;

    private static GymLogRepository repository;

    /**
     * Creates a new GymLogRepository using the given Application.
     * Initializes DAOs and pre-loads all logs.
     *
     * @param application the Application context
     */
    private GymLogRepository(Application application) {
        GymLogDatabase db = GymLogDatabase.getDatabase(application);
        this.gymLogDAO = db.gymLogDao();
        this.userDAO = db.userDAO();
        this.allLogs = (ArrayList<GymLog>) this.gymLogDAO.getAllRecords();
    }

    /**
     * Returns a singleton instance of the GymLogRepository.
     * The repository is created on a background thread.
     *
     * @param application the Application context
     * @return the GymLogRepository instance, or null if a thread error occurs
     */
    public static GymLogRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<GymLogRepository> future = GymLogDatabase.databaseWriteExecutor.submit(
                new Callable<GymLogRepository>() {
                    @Override
                    public GymLogRepository call() throws Exception {
                        return new GymLogRepository(application);
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting GymLogRepository, thread error.");
        }
        return null;
    }

    /**
     * Gets all GymLog records from the database on a background thread.
     *
     * @return a list of all GymLog records, or null if an error occurs
     */
    public ArrayList<GymLog> getAllLogs() {
        Future<ArrayList<GymLog>> future = GymLogDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<GymLog>>() {
                    @Override
                    public ArrayList<GymLog> call() throws Exception {
                        return (ArrayList<GymLog>) gymLogDAO.getAllRecords();
                    }
                });
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem when getting all GymLogs in the repository");
        }
        return null;
    }

    /**
     * Inserts a GymLog into the database on a background thread.
     *
     * @param gymLog the log entry to insert
     */
    public void insertGymLog(GymLog gymLog) {
        GymLogDatabase.databaseWriteExecutor.execute(()-> {
            gymLogDAO.insert(gymLog);
        });
    }

    /**
     * Inserts one or more users into the database on a background thread.
     *
     * @param user one or more User objects to insert
     */
    public void insertUser(User... user) {
        GymLogDatabase.databaseWriteExecutor.execute(()-> {
            userDAO.insert(user);
        });
    }

    /**
     * Returns a LiveData<User> found by username.
     *
     * @param username the username to look up
     * @return LiveData wrapping the User, or null if not found
     */
    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    /**
     * Returns a LiveData<User> found by user ID.
     *
     * @param userId the ID of the user to look up
     * @return LiveData wrapping the User, or null if not found
     */
    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    /**
     * Returns a LiveData list of GymLog entries for a given user ID.
     * This is used so the UI can observe and react to changes.
     *
     * @param loggedInUserId the ID of the logged-in user
     * @return LiveData list of GymLog entries
     */
    public LiveData<List<GymLog>> getAllLogsByUserIdLiveData(int loggedInUserId) {
        return gymLogDAO.getRecordsByUserIdLiveData(loggedInUserId);
    }

    /**
     * Returns a list of GymLog entries for a given user ID using a background thread.
     * This method is deprecated in favor of the LiveData version.
     *
     * @param loggedInUserId the ID of the logged-in user
     * @return list of GymLog entries, or null if an error occurs
     */
    @Deprecated
    public ArrayList<GymLog> getAllLogsByUserId(int loggedInUserId) {
        Future<ArrayList<GymLog>> future = GymLogDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<GymLog>>() {
                    @Override
                    public ArrayList<GymLog> call() throws Exception {
                        return (ArrayList<GymLog>) gymLogDAO.getRecordsByUserId(loggedInUserId);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem when getting all GymLogs in the repository");
        }
        return null;
    }
}

