package com.marshmallow.beehive;

import com.marshmallow.beehive.models.StoryModel;
import com.marshmallow.beehive.models.UserModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by George on 5/18/2018.
 */
public class UserModelUnitTest {
    private UserModel userModel = new UserModel();

    @Test
    public void testConstruction() {
        assertThat(userModel.getUserStory(), instanceOf(StoryModel.class));
    }

    @Test
    public void testAccountId() {
        String accountId = "testAccountId";
        userModel.setAccountId(accountId);
        assertEquals(userModel.getAccountId(), accountId);
    }

    @Test
    public void testName() {
        String name = "testName";
        userModel.setUserName(name);
        assertEquals(userModel.getUserName(), name);
    }

    @Test
    public void testQuickPitch() {
        String quickPitch = "testQuickPitch";
        userModel.setQuickPitch(quickPitch);
        assertEquals(userModel.getQuickPitch(), quickPitch);
    }

    @Test
    public void testUserStory() {
        StoryModel storyModel = new StoryModel();
        userModel.setUserStory(storyModel);
        assertEquals(userModel.getUserStory(), storyModel);
    }

    @Test
    public void testEmail() {
        String email = "testEmail@gmail.com";
        userModel.setEmail(email);
        assertEquals(userModel.getEmail(), email);
    }

    // TODO profile picture
}
