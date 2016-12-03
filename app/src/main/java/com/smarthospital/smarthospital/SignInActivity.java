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

    //Створює об'єкт який шукає активність яку нам потрібно для виклику тієї активності
    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, SignInActivity.class);
        return startIntent;
    }

    //Позиція логотопу в перемикачі
    private static final int CHILD_LOGO = 0;
    //Позиція крутілки в перемикачі
    private static final int CHILD_PROGRESS = 1;


    //Об'єкт де користувач вводить свій email
    EditText mEmailEditText;

    //Об'єкт де користувач вводить свій пароль
    EditText mPasswordEditText;

    //Надає доступ до входу в програму
    FirebaseAuth mAuth;

    //Перемикач між двома об'єктами
    ViewSwitcher mViewSwitcher;


    @Override
//Викликається при першому створенні активності
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Створює внутрішній вигляд запущеної першої активності
        setContentView(R.layout.activity_sign_in);

        //Ініціазізація об'єкту автентифікації(підготовка до роботи з програмою)
        mAuth = FirebaseAuth.getInstance();

//Ініціалізація об'єкту для введення email користувача
        mEmailEditText = (EditText) findViewById(R.id.edit_text_user_name);

//Ініціалізація об'єкту для введення пароля користувача
        mPasswordEditText = (EditText) findViewById(R.id.edit_text_password);
//Ініціалізація перемикача між об'єктами
        mViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);

//Знаходимо кнопку по id і встановлюємо слухача на цю кнопку
        findViewById(R.id.button_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Викликаєм метод на кнопку входу
                onSignInButtonClick();
            }
        });
//Знаходимо кнопку по id і встановлюємо слухача на цю кнопку
        findViewById(R.id.button_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Викликаєм метод на кнопку відновлення паролю
                onForgotPasswordButtonClick();
            }
        });

        //Знаходимо кнопку по id і встановлюємо слухача на цю кнопку
        findViewById(R.id.button_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Викликаєм метод на кнопку реєстрації користувача
                onSignUpButtonClick();

            }
        });
    }

    private void onSignInButtonClick() {

        doSignIn(getEmail(), getPassword());
    }

    //Цей метод звертається до об'єкта введення email і просить дати текст(getText),
    // і переводить його в стрінгу (String), і обрізає пробіли спочатку та в кінці
    @NonNull
    private String getEmail() {
        return mEmailEditText.getText().toString().trim();
    }

//Цей метод звертається до об'єкта введення паролю і просить дати текст(getText)
//переводить його в стрінгу (String), і обрізає пробіли спочатку та в кінці
    @NonNull
    private String getPassword() {
        return mPasswordEditText.getText().toString().trim();
    }

    //Цей метод викликається при натиску кнопки 'вхід'
    // використовуючи параметри стрінги email і пароль
    private void doSignIn(String email, String password) {
        //Перевіряє чи пароль та email правильні
        if (validateEmailAndPassword(email, password)) {
            //Перевірка з'єдання з Інтернетом
            if(!checkForConnection()) return;
            //Змінює логотип на крутілку
            switchView(CHILD_PROGRESS);

            //Після перевірки даних та з'єдання з Інтернетом відсилає введені дані на сервер
            mAuth.signInWithEmailAndPassword(email, password)
                    //Додаємо слухача коли користувач ввійшов на свій акаунт
                    .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        //Приходить відповідь про завершення входу в акаунт користувача
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Зміна крутілки на логотип
                            switchView(CHILD_LOGO);
                            //Виклик результату при успішності входу
                            if (task.isSuccessful()){
                                //Запуск main activity при успішному результаті входу
                                startActivity(new Intent(SignInActivity.this, HospitalsActivity.class));
                                //Завершує роботу цієї активності
                                finish();
                            } else {
                                //Якщо вхід виконався неправильно
                                // то показує повідомлення що вхід не вдався
                                showToast("Sign In Failed");
                            }
                        }
                    });
        }
    }
//Цей метод перевіряє на вірність введення email і пароля
    private boolean validateEmailAndPassword(String email, String password) {
        return validateEmail(email) && validatePassword(password);
    }

    //Перевірка на вірність введеня email
    private boolean validateEmail(String email) {
        //Перевіряє правильність введеня структури email
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        //Якщо Email введено невірно то показує що Email хибний
        showEmailError();
        return false;
    }

    //Цей метод викликається якщо Email введено неправильно
    private void showEmailError() {
        showToast("Email is incorrect");
    }
//Цей метод перевіряє на вірність введення пароля
    private boolean validatePassword(String password) {
       //Якщо пароль введено невірно то показує що пароль є хибний
        if (TextUtils.isEmpty(password)) {
            showPasswordError();
            return false;
        }
        return true;
    }

    //Цей метод викликається якщо пароль введено неправильно
    private void showPasswordError() {
        showToast("Password is incorrect");
    }

    //Цей метод перевіряє наявність Інтернет підключення
    private boolean checkForConnection(){
        //Якщо є Інтернет з'єдання то воно підтверджує що є з'єдання з Інтернетом
        if (ConnectionHelper.checkInternetConnection(SignInActivity.this)){
            return true;
        }
        //Якщо сталася помилка з'єднання з Інтернотом то показує помилку з'єдання
           showInternetConnectionError();
        return false;
    }

    //''Немає з'єдання'' - це повідомлення виводиться, коли немає Інтернет з'єдання
    private void showInternetConnectionError() {
        showToast("There is no Internet connection");
    }

//Цей метод показує коротке повідомлення
    private void showToast(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }
//Цей метод викликається при натисканні кнопки "Забув пароль"
    private void onForgotPasswordButtonClick() {

    }

//Цей метод викликається при натисканні кнопки реєстрації
    private void onSignUpButtonClick() {
        startActivity(RegisterActivity.getStartIntent(SignInActivity.this));
    }

//Цей метод логотип на крутілку
    private void switchView(int child){
        mViewSwitcher.setDisplayedChild(child);
    }
}

