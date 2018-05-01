package com.marshmallow.beehive.backendCommunications;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    public Boolean createUserWithEmailAndPassword(String email, String password) {
        Task<AuthResult> resultTask = firebaseAuth.createUserWithEmailAndPassword(email, password);
        resultTask.addOnCompleteListener(new OnCompleteListener< AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            }
        });

        return resultTask.isSuccessful();
    }

    @Override
    public Boolean signInWithEmailAndPassword(String email, String password) {
        Task<AuthResult> resultTask = firebaseAuth.signInWithEmailAndPassword(email, password);
        resultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            }
        });

        return resultTask.isSuccessful();
    }
}
