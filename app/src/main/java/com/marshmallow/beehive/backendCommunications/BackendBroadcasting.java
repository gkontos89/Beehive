package com.marshmallow.beehive.backendCommunications;

import android.content.Intent;
import android.os.Parcelable;

/**
 * This class will hold broadcast strings and status that the back end will broadcast and other activities
 * and processes can subscribe to
 *
 * Created by George on 5/2/2018.
 */
public final class BackendBroadcasting {

    public enum Status {
        CREATE_ACCOUNT_SUCCESSFUL,
        CREATE_ACCOUNT_FAILED,
        SIGN_IN_SUCCESSFUL,
        SIGN_IN_FAILED;

        private static final String name = Status.class.getName();

        // Sender usage
        public void attachTo(Intent intent) {
            intent.putExtra(name, ordinal());
        }

        // Receiver usage
        public static Status detachFrom(Intent intent) {
            if (!intent.hasExtra(name)) {
                throw new IllegalStateException();
            }

            return values()[intent.getIntExtra(name, -1)];
        }
    }

    private static final String createAccountStatusAction = "createAccountStatusAction";
    private static final String signInStatusAction = "signInStatusAction";
    private static final String failureInfoExtra = "failureInfoExtra";

    public static final String getCreateAccountStatusAction() { return createAccountStatusAction; }
    public static final String getSignInStatusAction() { return signInStatusAction; }
    public static final String getFailureInfoExtra() { return failureInfoExtra; }

    //TODO wrap intents in here and what needs to go in them



}
