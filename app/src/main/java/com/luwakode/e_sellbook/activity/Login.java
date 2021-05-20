package com.luwakode.e_sellbook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.luwakode.e_sellbook.MainActivity;
import com.luwakode.e_sellbook.R;
import com.luwakode.e_sellbook.databinding.ActivityLoginBinding;
import com.luwakode.e_sellbook.helper.PrefManager;
import com.luwakode.e_sellbook.helper.ServerApi;
import com.luwakode.e_sellbook.helper.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    AlertDialog dialog;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefManager = new PrefManager(this);
        if (prefManager.getBoolean("login")){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Sedang mencoba login...")
                .setCancelable(false)
                .build();

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
                    login(binding.txtemail.getText().toString(), binding.txtpassword.getText().toString());
                }
            }
        });
    }

    private void login(final String email, final String password) {
        dialog.show();
        // Tag biasanya digunakan ketika ingin membatalkan request volley
        String tag_string_req = "req_login";
        StringRequest strReq = new StringRequest(Request.Method.POST, ServerApi.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "Login Response: " + response.toString());
                try
                {
                    JSONObject res = new JSONObject(response);
                    boolean success = res.getBoolean("status");
                    dialog.dismiss();
                    // ngecek node error dari api
                    if (!success){
                        snackBar("Nip atau password anda tidak sesuai", R.color.error);
                    }else {
                        // user berhasil login
                        String id = res.getString("id");
                        //Set session di pref manager
                        prefManager.setString("id", id);
                        prefManager.setBoolean("login", true);
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    }
                } catch (JSONException e) {
                    // JSON error
                    dialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Login Error: " + error.getMessage());
                dialog.dismiss();
                //cek error timeout, noconnection dan network error
                if ( error instanceof TimeoutError || error instanceof NoConnectionError ||error instanceof NetworkError) {
                    snackBar("Sepertinya ada yang salah dengan koneksi anda", R.color.error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // kirim parameter ke server
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // menggunakan fungsi volley adrequest yang kita taro di appcontroller
        VolleySingleton.getInstance(this).addToRequestQueue(strReq);
    }

    private void snackBar(String pesan, int warna){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), pesan, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), warna));
        snackbar.show();
    }
}