package com.smarthospital.smarthospital;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

//   викликає метод onCreate при створенні або перезапуску активності
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//   задає зовнішній вигляд
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//   перевіряє чи зареєстрований такий користувач на FireBase
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {

//       якщо користувача немає, то запускається SignInActivity
            startSignInActivity();
            return;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//       коли юзер тисне на цю кнопку відбувається LogOut
                FirebaseAuth.getInstance().signOut();

//       після LogOut стартуємо SignInActivity
                startSignInActivity();
            }
        });
    }

//   цей метод стартує SignInActivity
    private void startSignInActivity() {
//        цим методом можна стартанути будь-яку Activity, в нашому випадку SignInActivity
        startActivity(SignInActivity.getStartIntent(MainActivity.this));

//   завершує роботу активності
        finish();
    }
}
