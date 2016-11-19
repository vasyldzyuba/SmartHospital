package com.smarthospital.smarthospital;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignUpActivity.class);
        return startIntent;
    }

    private TextInputEditText mEmailEditText;
    private TextInputEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mEmailEditText = (TextInputEditText) findViewById(R.id.email_edit_text);
        mPasswordEditText=(TextInputEditText) findViewById(R.id.password_edit_text);
       findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(SignUpActivity.this, mEmailEditText.getText(), Toast.LENGTH_SHORT).show();
           }
       });
    }
}
