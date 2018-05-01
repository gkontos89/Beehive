package com.marshmallow.beehive.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.backendCommunications.BeehiveBackend;
import com.marshmallow.beehive.ui.home.HomeActivity;
import com.marshmallow.beehive.ui.welcome.WelcomeActivity;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView emailTextEntry;
    private TextView passwordTextEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set GUI handles
        emailTextEntry = findViewById(R.id.email_text);
        passwordTextEntry = findViewById(R.id.password_text);
        findViewById(R.id.create_account_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is already signed in and if so move to home screen
        if (BeehiveBackend.getInstance().isUserSignedIn()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        // Handle Account creation
        if (view.getId() == R.id.create_account_button) {
            createAccount(emailTextEntry.getText().toString(), passwordTextEntry.getText().toString());
        }
        // Handle Sign in
        else if (view.getId() == R.id.sign_in_button) {
            signIn(emailTextEntry.getText().toString(), passwordTextEntry.getText().toString());
        }
    }

    private void createAccount(String email, String password) {
        if (BeehiveBackend.getInstance().createUserWithEmailAndPassword(email, password)) {
            accountCreationSuccess();
        } else {
            accountCreationFailed();
        }
    }

    public void accountCreationSuccess() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void accountCreationFailed() {

    }

    private void signIn(String email, String password) {
        if (BeehiveBackend.getInstance().signInWithEmailAndPassword(email, password)) {
            signInSucceeded();
        } else {
            signInFailed();
        }
    }

    public void signInSucceeded() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void signInFailed() {

    }
}
