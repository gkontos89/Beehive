package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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

    public FirebaseAuth getFirebaseAuth() { return firebaseAuth; }

    @Override
    public Boolean isUserSignedIn() {
        return (firebaseAuth.getCurrentUser() != null);
    }

    @Override
    public Boolean createUserWithEmailAndPassword(final Activity activity, String email, String password) {
        Task<AuthResult> resultTask = firebaseAuth.createUserWithEmailAndPassword(email, password);
        resultTask.addOnCompleteListener(activity, new OnCompleteListener< AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("hello", "done!?");
            }
        });

        return resultTask.isSuccessful();
    }

    @Override
    public Boolean signInWithEmailAndPassword(Context context, String email, String password) {
        Task<AuthResult> resultTask = firebaseAuth.signInWithEmailAndPassword(email, password);
        resultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            }
        });

        return resultTask.isSuccessful();
    }
}
