package com.example.hw03_gymlog.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hw03_gymlog.database.entities.GymLog;
import com.example.hw03_gymlog.MainActivity;
import com.example.hw03_gymlog.database.entities.User;
import com.example.hw03_gymlog.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * Main Room database class for the GymLog application.
 * Provides access to DAOs and creates the database instance.
 */
@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {GymLog.class, User.class}, version = 1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {

    public static final String USER_TABLE = "usertable";
    private static final String DATABASE_NAME = "GymLogdatabase";
    public static final String GYM_LOG_TABLE = "gymLogTable";

    private static volatile  GymLogDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Returns the active database instance.
     * Creates the database if it has not been created yet.
     *
     * @param context application context
     * @return the GymLogDatabase instance
     */
    public static GymLogDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (GymLogDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GymLogDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback executed when the database is created for the first time.
     * Adds default users (admin and test users).
     */
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser1 = new User("testUser1" , "testuser1");
                dao.insert(testUser1);
            });
        }
    };

    /**
     * Provides access to GymLog database operations.
     */
    public abstract GymLogDAO gymLogDao();

    /**
     * Provides access to User database operations.
     */
    public abstract UserDAO userDAO();
}
