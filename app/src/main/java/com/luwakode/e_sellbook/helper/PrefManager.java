package com.luwakode.e_sellbook.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "data_app";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setString(String key, String value){
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key){
        return pref.getString(key, "");
    }

    public void setBoolean(String key, Boolean value){
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key){
        return pref.getBoolean(key, false);
    }
}
