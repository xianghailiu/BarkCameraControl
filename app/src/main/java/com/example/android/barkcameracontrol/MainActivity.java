package com.example.android.barkcameracontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.barkcameracontrol.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, CameraControlFragment.newInstance())
                    .commitNow();
        }
    }
}