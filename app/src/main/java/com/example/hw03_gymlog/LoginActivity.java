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
     * Runs when the activity starts.
     * Sets up view binding and assigns the login button action.
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

    /**
     * Verifies user login credentials by checking the username and password.
     * If valid, opens MainActivity with the user ID.
     * If invalid, shows helpful error messages.
     */
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
     * Displays a short on-screen message.
     *
     * @param message the message text to display
     */
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates an Intent for starting the LoginActivity.
     *
     * @param context the context requesting this activity
     * @return an Intent to start LoginActivity
     */
    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}