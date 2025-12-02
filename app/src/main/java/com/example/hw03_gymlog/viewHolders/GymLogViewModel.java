package com.example.hw03_gymlog.viewHolders;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hw03_gymlog.database.GymLogRepository;
import com.example.hw03_gymlog.database.entities.GymLog;
import java.util.List;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * GymLogViewModel provides data to the UI and acts as a communication center between
 * the Repository and the UI components. It is responsible for managing and handling
 * data for GymLog entities in a lifecycle-conscious way.
 */
public class GymLogViewModel extends AndroidViewModel {
    private final GymLogRepository repository;

    /**
     * Creates a new ViewModel and initializes the repository.
     *
     * @param application the Application context
     */
    public GymLogViewModel(Application application) {
        super(application);
        repository = GymLogRepository.getRepository(application);
    }

    /**
     * Returns a LiveData list of GymLog entries for the specified user.
     *
     * @param userId the ID of the user whose logs should be retrieved
     * @return LiveData containing a list of GymLog items
     */
    public LiveData<List<GymLog>> getAllLogsById(int userId) {
        return repository.getAllLogsByUserIdLiveData(userId);
    }

    /**
     * Inserts a new GymLog into the repository.
     *
     * @param log the GymLog entry to insert
     */
    public void insert(GymLog log) {
        repository.insertGymLog(log);
    }
}
