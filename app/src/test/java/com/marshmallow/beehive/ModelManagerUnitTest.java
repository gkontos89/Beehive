package com.marshmallow.beehive;

import android.view.Display;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.CareerPositionModel;
import com.marshmallow.beehive.models.ModelManager;
import com.marshmallow.beehive.models.UserModel;

import org.junit.Test;

/**
 * Created by George on 5/18/2018.
 */
public class ModelManagerUnitTest {
    @Test
    public void testSingleton() {
        ModelManager modelManager1 = ModelManager.getInstance();
        ModelManager modelManager2 = ModelManager.getInstance();
        assertEquals(modelManager1, modelManager2);
    }

    @Test
    public void testGetUserModel() {
        assertNotEquals(ModelManager.getInstance().getUserModel(), null);
        assertThat(ModelManager.getInstance().getUserModel(), instanceOf(UserModel.class));

        UserModel userModel1 = ModelManager.getInstance().getUserModel();
        UserModel userModel2 = ModelManager.getInstance().getUserModel();
        assertEquals(userModel1, userModel2);
    }

    @Test
    public void testGetAccountId() {
        ModelManager.getInstance().setAccountId("testString");
        assertThat(ModelManager.getInstance().getAccountId(), instanceOf(String.class));
        String accountId1 = ModelManager.getInstance().getAccountId();
        String accountId2 = ModelManager.getInstance().getAccountId();
        assertEquals(accountId1, accountId2);
    }

    @Test
    public void testGenerateNewCareerPointModel() {
        ModelManager.getInstance().generateNewCareerPointModel();
        assertThat(ModelManager.getInstance().getActiveCareerPointModel(), instanceOf(CareerPointModel.class));

        CareerPointModel activeCareerPointModel1 = ModelManager.getInstance().getActiveCareerPointModel();
        ModelManager.getInstance().generateNewCareerPointModel();
        CareerPointModel activeCareerPointModel2 = ModelManager.getInstance().getActiveCareerPointModel();
        assertNotEquals(activeCareerPointModel1, activeCareerPointModel2);
    }

    @Test
    public void testSetActiveCareerPointModel() {
        CareerPointModel careerPointModel = new CareerPointModel();
        ModelManager.getInstance().setActiveCareerPointModel(careerPointModel);
        assertEquals(careerPointModel, ModelManager.getInstance().getActiveCareerPointModel());
    }

    @Test
    public void testRemoveActiveCareerPointModel() {
        ModelManager.getInstance().removeActiveCareerPointModel();
        assertEquals(ModelManager.getInstance().getActiveCareerPointModel(), null);
    }

    @Test
    public void testSaveActiveCareerPointModel() {
        CareerPointModel careerPointModel = new CareerPointModel();
        assertFalse(ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().contains(careerPointModel));

        ModelManager.getInstance().setActiveCareerPointModel(careerPointModel);
        ModelManager.getInstance().saveActiveCareerPointModel();

        assertTrue(ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().contains(careerPointModel));
        assertEquals(ModelManager.getInstance().getActiveCareerPointModel(), null);
    }

    @Test
    public void testUserStoryContainsActiveCareerPointModel() {
        CareerPointModel careerPointModel = new CareerPointModel();
        ModelManager.getInstance().setActiveCareerPointModel(careerPointModel);
        assertFalse(ModelManager.getInstance().userStoryContainsActiveCareerPointModel());

        ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().add(careerPointModel);
        assertTrue(ModelManager.getInstance().userStoryContainsActiveCareerPointModel());
    }

    @Test
    public void testGenerateNewCareerPointPositionModel() {
        CareerPositionModel careerPositionModel = ModelManager.getInstance().getActiveCareerPointPositionModel();
        ModelManager.getInstance().generateNewCareerPointPositionModel();
        assertNotEquals(careerPositionModel, ModelManager.getInstance().getActiveCareerPointPositionModel());
    }

    @Test
    public void testSetActiveCareerPointPositionModel() {
        CareerPositionModel careerPositionModel = new CareerPositionModel();
        ModelManager.getInstance().setActiveCareerPointPositionModel(careerPositionModel);
        assertEquals(careerPositionModel, ModelManager.getInstance().getActiveCareerPointPositionModel());
    }

    @Test
    public void testRemoveActiveCareerPositionModel() {
        ModelManager.getInstance().generateNewCareerPointModel();
        CareerPositionModel careerPositionModel = new CareerPositionModel();
        ModelManager.getInstance().setActiveCareerPointPositionModel(careerPositionModel);
        ModelManager.getInstance().removeActiveCareerPositionModel();
        assertEquals(ModelManager.getInstance().getActiveCareerPointPositionModel(), null);
        assertFalse(ModelManager.getInstance().getActiveCareerPointModel().getCareerPositionModels().contains(careerPositionModel));
    }

    @Test
    public void testSaveActiveCareerPointPositionModel() {
        ModelManager.getInstance().saveActiveCareerPointPositionModel();
        assertEquals(ModelManager.getInstance().getActiveCareerPointPositionModel(), null);
    }
}

