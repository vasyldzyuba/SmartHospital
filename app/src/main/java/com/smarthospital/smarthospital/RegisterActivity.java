package com.smarthospital.smarthospital;
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

public class RegisterActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, RegisterActivity.class);
        return startIntent;
    }

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);

        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(RegisterActivity.this, mEmailEditText.getText(), Toast.LENGTH_SHORT).show();
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
                    .addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            showToast("createUserWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()) {
                                startMainActivity();
                            } else {
                               showToast("Authentification failed" + task.getException());

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
        showToast("Your email is incorrect");
    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showPasswordError();
            return false;
        }
        return true;

    }

    private void showPasswordError() {
        showToast("Your password is incorrect");
    }

    private void showConnectionError(){
        showToast("There is no internet connection");
    }

    private void showToast(String message){
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void startMainActivity(){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

}



