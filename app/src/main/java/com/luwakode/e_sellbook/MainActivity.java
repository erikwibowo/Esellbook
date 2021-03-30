package com.luwakode.e_sellbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luwakode.e_sellbook.activity.Laporan;
import com.luwakode.e_sellbook.activity.Pindai;
import com.luwakode.e_sellbook.databinding.ActivityMainBinding;
import com.luwakode.e_sellbook.helper.Helper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Helper.give_permission(this);
        binding.cardScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pindai.class));
            }
        });
        binding.cardLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Laporan.class));
            }
        });
    }
}