package com.smith.uimodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UiMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity_layout);
    }
}
