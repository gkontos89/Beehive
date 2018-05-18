package com.marshmallow.beehive.backendCommunications.broadcasts;

import android.content.Intent;

/**
 * Created by George on 5/17/2018.
 */
public class CreateUserStatusBroadcast {

    // Returned Intent
    private Intent intent;

    // Keys
    public static final String statusMessageKey = "statusMessage";
    public static final String dataKey = "data";
    public static final String statusKey = "status";

    // Fields
    public static final String action = "CreateUserStatusBroadcast";
    private String statusMessage = null;
    private String data = null;

    // Statuses
    public static final String CREATE_ACCOUNT_SUCCESSFUL = "CREATE_ACCOUNT_SUCCESSFUL";
    public static final String CREATE_ACCOUNT_FAILED = "CREATE_ACCOUNT_FAILED";

    public CreateUserStatusBroadcast(String statusMessage, String data) {
        intent = new Intent();
        this.statusMessage = statusMessage;
        this.data = data;

        intent.setAction(action);
        intent.putExtra(dataKey, data);
        intent.putExtra(statusMessageKey, statusMessage);
    }

    public Intent getSuccessfulBroadcast() {
        intent.putExtra(statusKey, CREATE_ACCOUNT_SUCCESSFUL);
        return intent;
    }

    public Intent getFailureBroadcast() {
        intent.putExtra(statusKey, CREATE_ACCOUNT_FAILED);
        return intent;
    }
}
