package com.example.ihubtechnologies.superdocnew.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ihubtechnologies.superdocnew.activities.LoginActivity;


/**
 * Created by Aparna on 14-06-2018.
 */

public class SessionManager {
    private static SessionManager sessionManager;
    static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;


    public static final String DOCTORID = "doctorid";





    public void setDOCTORID(String doctorid) {
        editor.putString(DOCTORID, doctorid);
        editor.commit();
    }

    public static String getDOCTORID() {
        return sharedPreferences.getString(DOCTORID, null);
    }



    public static SessionManager getInstance(Context context) {
        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("IHUB_DB", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }
}


