package com.smarthospital.smarthospital.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smarthospital.smarthospital.R;

/**
 * Created by HOME-PC on 03.12.2016.
 */


public class HospitalDetailsActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, HospitalDetailsActivity.class);
        return startIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

    }
}


