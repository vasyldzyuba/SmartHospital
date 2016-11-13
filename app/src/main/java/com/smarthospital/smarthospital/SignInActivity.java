package com.smarthospital.smarthospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HOME-PC on 12.11.2016.
 */

public class SignInActivity extends AppCompatActivity{

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignInActivity.class);
        return startIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
