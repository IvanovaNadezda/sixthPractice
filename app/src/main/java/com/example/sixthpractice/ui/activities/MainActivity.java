package com.example.sixthpractice.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sixthpractice.R;
import com.example.sixthpractice.ServiceClass;
import com.example.sixthpractice.data.repository.SharedPreferencesRepository;
import com.example.sixthpractice.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, ServiceClass.class);
        stopService(intent);
    }
}