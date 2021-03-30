package com.luwakode.e_sellbook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luwakode.e_sellbook.MainActivity;
import com.luwakode.e_sellbook.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.txtemail.getText().toString().isEmpty()){
                    binding.txtemail.setError("Harap ketikkan email anda");
                    binding.txtemail.requestFocus();
                }else if (binding.txtpassword.getText().toString().isEmpty()){
                    binding.txtpassword.setError("Harap ketikkan password anda");
                    binding.txtpassword.requestFocus();
                }
                else{
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}