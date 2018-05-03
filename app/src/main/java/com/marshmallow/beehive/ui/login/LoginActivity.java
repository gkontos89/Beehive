package com.marshmallow.beehive.ui.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.backendCommunications.BackendBroadcasting;
import com.marshmallow.beehive.backendCommunications.BeehiveBackend;
import com.marshmallow.beehive.ui.home.HomeActivity;
import com.marshmallow.beehive.ui.welcome.WelcomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView emailTextEntry;
    private TextView passwordTextEntry;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set GUI handles
        emailTextEntry = findViewById(R.id.email_text);
        passwordTextEntry = findViewById(R.id.password_text);
        findViewById(R.id.create_account_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Set up broadcastReceiver and its filter
        intentFilter = new IntentFilter();
        intentFilter.addAction(BackendBroadcasting.getCreateAccountStatusAction());
        intentFilter.addAction(BackendBroadcasting.getSignInStatusAction());

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Handle back end status pertaining to the login screen
                if (intent.getAction().equals(BackendBroadcasting.getCreateAccountStatusAction())) {
                    BackendBroadcasting.Status status = BackendBroadcasting.Status.detachFrom(intent);
                    if (status == BackendBroadcasting.Status.CREATE_ACCOUNT_SUCCESSFUL) {
                        accountCreationSuccess();
                    } else if (status == BackendBroadcasting.Status.CREATE_ACCOUNT_FAILED) {
                        accountCreationFailed(intent.getStringExtra(BackendBroadcasting.getFailureInfoExtra()));
                    } else {
                        // TODO throw custom error
                    }
                } else if (intent.getAction().equals(BackendBroadcasting.getSignInStatusAction())) {
                    BackendBroadcasting.Status status = BackendBroadcasting.Status.detachFrom(intent);
                    if (status == BackendBroadcasting.Status.SIGN_IN_SUCCESSFUL) {
                        signInSucceeded();
                    } else if (status == BackendBroadcasting.Status.SIGN_IN_FAILED) {
                        signInFailed(intent.getStringExtra(BackendBroadcasting.getFailureInfoExtra()));
                    }
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
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
            BeehiveBackend.getInstance().createUserWithEmailAndPassword(getApplicationContext(),
                    this,
                    emailTextEntry.getText().toString(),
                    passwordTextEntry.getText().toString());
        }
        // Handle Sign in
        else if (view.getId() == R.id.sign_in_button) {
            BeehiveBackend.getInstance().signInWithEmailAndPassword(getApplicationContext(),
                    this,
                    emailTextEntry.getText().toString(),
                    passwordTextEntry.getText().toString());
        }
    }

    public void accountCreationSuccess() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void accountCreationFailed(String failureString) {
        Toast.makeText(this, "Account Creation Failed: " + failureString, Toast.LENGTH_SHORT).show();
    }

    public void signInSucceeded() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void signInFailed(String failureString) {
        Toast.makeText(this, "Sign In Failed: " + failureString, Toast.LENGTH_SHORT).show();
    }
}
