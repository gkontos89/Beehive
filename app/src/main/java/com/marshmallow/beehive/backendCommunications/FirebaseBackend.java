package com.marshmallow.beehive.backendCommunications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.marshmallow.beehive.ui.login.LoginActivity;

/**
 * This class implements Firebase backend communications
 *
 * Created by George on 4/29/2018.
 */
public class FirebaseBackend implements BeehiveBackendInterface {
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;

    public FirebaseBackend() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    @Override
    public Boolean isUserSignedIn() {
        return (firebaseAuth.getCurrentUser() != null);
    }

    @Override
    public void createUserWithEmailAndPassword(final Context context, final Activity activity, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener< AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent();
                        intent.setAction(BackendBroadcasting.getCreateAccountStatusAction());
                        if (task.isSuccessful()) {
                            BackendBroadcasting.Status status = BackendBroadcasting.Status.CREATE_ACCOUNT_SUCCESSFUL;
                            status.attachTo(intent);
                        } else {
                            BackendBroadcasting.Status status = BackendBroadcasting.Status.CREATE_ACCOUNT_FAILED;
                            status.attachTo(intent);
                            intent.putExtra(BackendBroadcasting.getFailureInfoExtra(), task.getException().getMessage());
                        }

                        context.sendBroadcast(intent);
                    }
                });
    }

    @Override
    public void signInWithEmailAndPassword(final Context context, final Activity activity, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent();
                        intent.setAction(BackendBroadcasting.getSignInStatusAction());
                        if (task.isSuccessful()) {
                            BackendBroadcasting.Status status = BackendBroadcasting.Status.SIGN_IN_SUCCESSFUL;
                            status.attachTo(intent);
                        } else {
                            BackendBroadcasting.Status status = BackendBroadcasting.Status.SIGN_IN_FAILED;
                            status.attachTo(intent);
                            intent.putExtra(BackendBroadcasting.getFailureInfoExtra(), task.getException().getMessage());
                        }

                        context.sendBroadcast(intent);
                    }
                });
    }

    @Override
    public void signOutUser() {
        firebaseAuth.signOut();
    }
}
