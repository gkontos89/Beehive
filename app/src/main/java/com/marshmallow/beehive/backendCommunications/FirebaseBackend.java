package com.marshmallow.beehive.backendCommunications;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.marshmallow.beehive.ui.login.LoginActivity;

/**
 * This class implements Firebase backend communications
 *
 * Created by George on 4/29/2018.
 */
public class FirebaseBackend implements BeehiveBackendInterface {
    private FirebaseAuth firebaseAuth;

    public FirebaseBackend() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Boolean isUserSignedIn() {
        return (firebaseAuth.getCurrentUser() != null);
    }

    @Override
    public void createUserWithEmailAndPassword(final Context context, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener< AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    ((LoginActivity) context).accountCreationSuccess();
                } else {
                    ((LoginActivity) context).accountCreationFailed();
                }
            }
        });
    }

    @Override
    public void signInWithEmailAndPassword(final Context context, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            ((LoginActivity) context).signInSucceeded();
                        } else {
                            // If sign in fails, display a message to the user.
                            ((LoginActivity) context).signInFailed();
                        }
                    }
                });
    }
}
