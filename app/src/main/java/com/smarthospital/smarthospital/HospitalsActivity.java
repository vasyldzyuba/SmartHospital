package com.smarthospital.smarthospital;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HospitalsActivity extends AppCompatActivity {
    //Викликає метод Main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startSignInActivity();
            return;
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startSignInActivity();
            }
        });
    }

    private void startSignInActivity() {
        startActivity(SignInActivity.getStartIntent(HospitalsActivity.this));
        finish();
    }
}
