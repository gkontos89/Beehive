package com.marshmallow.beehive;

import android.content.Intent;

import com.marshmallow.beehive.backendCommunications.BackendBroadcasting;

import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by George on 5/2/2018.
 */
public class BackendBroadcastingUnitTest {

    // Broadcast string testing
    @Test
    public void testGetCreateAccountStatusAction() {
        assertEquals(BackendBroadcasting.getCreateAccountStatusAction(), "createAccountStatusAction");
    }

    @Test
    public void testGetSignInStatusAction() {
        assertEquals(BackendBroadcasting.getSignInStatusAction(), "signInStatusAction");
    }

    @Test
    public void testGetFailureInfoExtra() {
        assertEquals(BackendBroadcasting.getFailureInfoExtra(), "failureInfoExtra");
    }

    // Status Enum testing
    @Test
    public void testCreateAccountSuccessfulStatus() {
        assertThat(BackendBroadcasting.Status.valueOf("CREATE_ACCOUNT_SUCCESSFUL"), is(notNullValue()));
    }

    @Test
    public void testCreateAccountFailedStatus() {
        assertThat(BackendBroadcasting.Status.valueOf("CREATE_ACCOUNT_FAILED"), is(notNullValue()));
    }

    @Test
    public void testSignInSuccessfulStatus() {
        assertThat(BackendBroadcasting.Status.valueOf("SIGN_IN_SUCCESSFUL"), is(notNullValue()));
    }

    @Test
    public void testSignInFailedStatus() {
        assertThat(BackendBroadcasting.Status.valueOf("SIGN_IN_FAILED"), is(notNullValue()));
    }
}
