package com.smarthospital.smarthospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smarthospital.smarthospital.util.ConnectionHelper;

/**
 * Created by HOME-PC on 12.11.2016.
 */

public class SignInActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignInActivity.class);
        return startIntent;
    }
    private static final int CHILD_LOGO = 0;
    private static final int CHILD_PROGRESS = 1;


    EditText mEmailEditText;
    EditText mPasswordEditText;
    FirebaseAuth mAuth;
    ViewSwitcher mViewSwitcher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        mEmailEditText = (EditText) findViewById(R.id.edit_text_user_name);
        mPasswordEditText = (EditText) findViewById(R.id.edit_text_password);
        mViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);

        findViewById(R.id.button_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignInButtonClick();
            }
        });

        findViewById(R.id.button_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onForgotPasswordButtonClick();
            }
        });

        findViewById(R.id.button_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpButtonClick();

            }
        });
    }

    private void onSignInButtonClick() {
        doSignIn(getEmail(), getPassword());
    }

    @NonNull
    private String getEmail() {
        return mEmailEditText.getText().toString().trim();
    }

    @NonNull
    private String getPassword() {
        return mPasswordEditText.getText().toString().trim();
    }

    private void doSignIn(String email, String password) {
        if (validateEmailAndPassword(email, password)) {
            if(!checkForConnection()) return;
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }

    private boolean validateEmailAndPassword(String email, String password) {
        return validateEmail(email) && validatePassword(password);
    }

    private boolean validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        showEmailError();
        return false;
    }

    private void showEmailError() {
        showToast("Email is incorrect");
    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showPasswordError();
            return false;
        }
        return true;
    }

    private boolean checkForConnection(){
        if (ConnectionHelper.checkInternetConnection(SignInActivity.this)){
            return true;
        }
           showInternetConnectionError();
        return false;
    }

    private void showInternetConnectionError() {
        showToast("There is no Internet connection");
    }

    private void showPasswordError() {
        showToast("Password is incorrect");
    }

    private void showToast(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void onForgotPasswordButtonClick() {
        Toast.makeText(SignInActivity.this, mPasswordEditText.getText(), Toast.LENGTH_SHORT).show();
    }


    private void onSignUpButtonClick() {
        startActivity(SignUpActivity.getStartIntent(SignInActivity.this));
    }

    private void switchView(int child){
        mViewSwitcher.setDisplayedChild(child);
    }
}

