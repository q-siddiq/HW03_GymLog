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
    public GymLogViewModel(Application application) {
        super(application);
        repository = GymLogRepository.getRepository(application);
    }

    public LiveData<List<GymLog>> getAllLogsById(int userId) {
        return repository.getAllLogsByUserIdLiveData(userId);
    }

    public void insert(GymLog log) {
        repository.insertGymLog(log);
    }
}
