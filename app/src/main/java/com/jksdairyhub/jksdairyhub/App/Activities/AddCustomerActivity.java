package com.jksdairyhub.jksdairyhub.App.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jksdairyhub.jksdairyhub.R;

import java.util.Objects;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}