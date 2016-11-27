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
import com.smarthospital.smarthospital.util.ConnectionHelper;

public class RegisterActivity extends AppCompatActivity {

    //об'єкт в якому и вибираємо яку активність взяти
    //цей метод створює новий обєкт intent
    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, RegisterActivity.class);
        return startIntent;
    }
        //створюємо об'єкт для вводу email
    private EditText mEmailEditText;
        //створюємо обєкт для вводу паролю
    private EditText mPasswordEditText;
        //створюємо обєкт для автентифікації
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Задає зовнішній вигляд Register activity
        setContentView(R.layout.activity_register);
        //Ініціалізуєм об'єкт автентифікації
        mAuth = FirebaseAuth.getInstance();
        //ініціалізуєм об'єкт вводу паролю
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        //Ініціалізуєм об'єкт вводу паролю
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
           //Шукає користувача за кліком,і вішає на слухача цю кнопку
        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Бере в користувача email і пароль
                doRegister(getEmail(), getPassword());
            }
        });
    }
    //Бере стрічку email і обрізає її з боків де пробіли
    @NonNull
    private String getEmail() {
        return mEmailEditText.getText().toString().trim();
    }
    //Бере стрічку пароль і обрізає її з боків де пробіли
    @NonNull
    private String getPassword() {
        return mPasswordEditText.getText().toString().trim();
    }
    //Метод який починає реєстрацію
    private void doRegister(String email, String password) {
        //Перевіряє чи email і пароль є валідними
        if (validateEmailAndPassword(email, password)){
            //Перевіряє чи є інтернет-з'єднання
            if(lacksInternetConnection()) return;
            //Викликаємо метод який створює юзера з певним паролем і емейлом на сервері
            mAuth.createUserWithEmailAndPassword(email, password)
                    //Я віправив на сервер дані,щоб послухати ті дані
                    .addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Якщо операція успішна, то розпочати головну активність
                            if (task.isSuccessful()) {
                                startMainActivity();
                                //В іншому випадку показати повідомлення про помилку
                            } else {
                               showToast("Authentification failed");

                            }
                        }
                    });
        }
    }
    //Метод для валідації імейлу та паролю
    private boolean validateEmailAndPassword(String email, String password) {
        //Якщо валідація true, то повернути імейл та пароль
      return validateEmail(email) && validatePassword(password);
    }
    //Окреми метод для валідації імейлу
    private boolean validateEmail(String email){
        //Якщо імейл відповідає шаблону для імейлів
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        //Якщо false то показати повідомлення про помилку
        showEmailError();
        return false;
    }
    //Створюємо метод який показує повідомлення про некоректність емейлу

    private void showEmailError() {
        showToast("Your email is incorrect");
    }
    //Створюємо метод для валідації паролю
    private boolean validatePassword(String password) {
        //Якщо поле для паролю пусте
        if (TextUtils.isEmpty(password)) {
            //Показує повідомлення про помилку
            showPasswordError();
            return false;
        }
        return true;

    }

    //Метод який показує повідомлення про некоректність паролю
    private void showPasswordError() {
        showToast("Your password is incorrect");
    }

    // Метод який буде показувати помилки про всяку херню
    private void showToast(String message){
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
    }

    //Метод який починає головну активність
    private void startMainActivity(){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

     //Метод який перевіряє зєднання з інтернетом
    private boolean lacksInternetConnection() {
       //Якщо німецькі фільми качаються скоро
        if(ConnectionHelper.checkInternetConnection(RegisterActivity.this)){
            return false;
        }
        //Якщо зєднання хренове як в селі
        showConnectionError();
        return true;
    }

    //Метод який показує повідомлення про відсутність інтернет-зєдняння
    private void showConnectionError(){
        showToast("There is no internet connection");
    }

}



