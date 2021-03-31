package com.luwakode.e_sellbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.luwakode.e_sellbook.activity.Laporan;
import com.luwakode.e_sellbook.activity.Login;
import com.luwakode.e_sellbook.activity.Pindai;
import com.luwakode.e_sellbook.databinding.ActivityMainBinding;
import com.luwakode.e_sellbook.helper.Helper;
import com.luwakode.e_sellbook.helper.PrefManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Helper.give_permission(this);
        prefManager = new PrefManager(this);
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
        binding.fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apakah anda yakin akan keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                prefManager.setBoolean("login", false);
                                prefManager.setString("id", "");
                                Intent intent = new Intent(MainActivity.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", null);
                builder.create();
                builder.show();
            }
        });
    }
}