package com.smarthospital.smarthospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by HOME-PC on 12.11.2016.
 */

public class SignInActivity extends AppCompatActivity{

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignInActivity.class);
        return startIntent;
    }

    EditText mUsernameEditText;
    EditText mPasswordEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUsernameEditText = (EditText) findViewById(R.id.edit_text_user_name);
        mPasswordEditText = (EditText) findViewById(R.id.edit_text_password);

        findViewById(R.id.button_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignInActivity.this, mUsernameEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignInActivity.this,  mPasswordEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignInActivity.this,  "Я нажав кнопку Sign Up", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
