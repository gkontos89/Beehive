package com.marshmallow.beehive;

import com.marshmallow.beehive.models.CareerPositionModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by George on 5/18/2018.
 */
public class CareerPositionModelTest {
    private CareerPositionModel careerPositionModel = new CareerPositionModel();

    @Test
    public void testPositionName() {
        String positionName = "testPositionName";
        careerPositionModel.setPositionName(positionName);
        assertEquals(careerPositionModel.getPositionName(), positionName);
    }

    @Test
    public void testStartEndDate() {
        String startDate = "testStartDate";
        String endDate = "testEndDate";

        careerPositionModel.setStartDate(startDate);
        assertEquals(careerPositionModel.getStartDate(), startDate);
        careerPositionModel.setEndDate(endDate);
        assertEquals(careerPositionModel.getEndDate(), endDate);
    }

    @Test
    public void testSummary() {
        String summary = "testSummary";
        careerPositionModel.setSummary(summary);
        assertEquals(careerPositionModel.getSummary(), summary);
    }
}
