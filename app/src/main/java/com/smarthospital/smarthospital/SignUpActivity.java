package com.smarthospital.smarthospital;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignUpActivity.class);
        return startIntent;
    }

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);

        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(SignUpActivity.this, mEmailEditText.getText(), Toast.LENGTH_SHORT).show();
           }
       });

        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doSignUp(getEmail(), getPassword());
            }
        });
    }

    @NonNull
    private String getEmail() {
        return mEmailEditText.getText().toString().trim();
    }

    @NonNull
    private String getPassword() {
        return mPasswordEditText.getText().toString().trim();
    }

    private void doSignUp(String email, String password) {
        if (validateEmailAndPassword(email, password)){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean validateEmailAndPassword(String email, String password) {
      return validateEmail(email) && validatePassword(password);
    }

    private boolean validateEmail(String email){
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        showEmailError();
        return false;
    }

    private void showEmailError() {
        Toast.makeText(SignUpActivity.this, "Your email is incorrect", Toast.LENGTH_SHORT).show();
    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showPasswordError();
            return false;
        }
        return true;

    }

    private void showPasswordError() {
        Toast.makeText(SignUpActivity.this, "Your password is incorrect", Toast.LENGTH_SHORT).show();
    }
}



