package com.example.hw03_gymlog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.hw03_gymlog.database.GymLogRepository;
import com.example.hw03_gymlog.database.entities.User;
import com.example.hw03_gymlog.databinding.ActivityLoginBinding;

/**
 * Author: Quratulain Siddiq
 * CST 338 GymLog
 * LoginActivity handles the login functionality of the GymLog application.
 * It allows users to log in using their credentials and access the main application features.
 */

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private GymLogRepository repository;

    /**
     * Called when the activity is starting. Initializes the activity, sets the content view,
     * and sets up the login button click listener.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     * then this Bundle contains the data it most recently supplied in onSaveInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = GymLogRepository.getRepository((getApplication()));

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });
    }

    private void verifyUser() {

        String username = binding.userNameLoginEditText.getText().toString();
        if (username.isEmpty()) {
            toastMaker("Username may not be blank.");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                String password = binding.passwordLoginEditText.getText().toString();
                if (password.equals(user.getPassword())) {
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                } else {
                    toastMaker("Invalid password");
                    binding.passwordLoginEditText.setSelection(0);
                }
            } else {
                toastMaker(String.format("%s is not a valid username", username));
                binding.userNameLoginEditText.setSelection(0);
            }
        });
    }

    /**
     * Displays a Toast message on the screen.
     * @param message the message to be displayed
     */
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates an Intent for starting the LoginActivity.
     * @param context the context from which the Intent is created
     * @return an Intent to start the LoginActivity
     */
    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }

}