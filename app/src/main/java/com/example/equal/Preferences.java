package com.example.equal;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences  {
    private static final String PREFS_NAME = "user_prefs";

    private static final String ID = "id_user";
    private static final String LOGGED_IN = "logged_in";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    private final SharedPreferences preference;

    Preferences(Context context){
        preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setLoggedIn(boolean status, int id){
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(ID, id);
        editor.putBoolean(LOGGED_IN, status);
        editor.apply();

    }

    public boolean getLoggedIn(){
        Boolean status = preference.getBoolean(LOGGED_IN, false);

        return status;
    }

    public String getUserId(){
        String id = preference.getString(ID, "");

        return id;
    }

    public void setProfile(String name, String email){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getName(){
        String name = preference.getString(NAME, "");

        return name;
    }

    public String getEmail(){
        String email = preference.getString(EMAIL, "");

        return email;
    }



}
