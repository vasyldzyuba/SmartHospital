package com.smarthospital.smarthospital;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HOME-PC on 12.11.2016.
 */

public class SmartHospitalAccount {

    private static final String ACCOUNT_PREF = "ACCOUNT_PREF";
    private static final String KEY_REGISTERED = "KEY_REGISTERED";

    private final SharedPreferences mSharedPreference;
    private static SmartHospitalAccount sInstance;


    public static SmartHospitalAccount get(Context context){
        if (sInstance == null) {
            sInstance = new SmartHospitalAccount(context);
        }
        return sInstance;
    }

    private SmartHospitalAccount(Context context) {
        mSharedPreference = context.getApplicationContext().getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
    }

    public boolean lacksRegistered() {
        return !mSharedPreference
                .getBoolean(KEY_REGISTERED, false);
    }

    public void setRegistered(boolean registered) {
        mSharedPreference
                .edit()
                .putBoolean(KEY_REGISTERED, registered)
                .apply();
    }

}

