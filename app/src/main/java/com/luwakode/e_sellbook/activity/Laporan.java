package com.luwakode.e_sellbook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.luwakode.e_sellbook.databinding.ActivityLaporanBinding;

public class Laporan extends AppCompatActivity {

    private ActivityLaporanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaporanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Laporan");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}