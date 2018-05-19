package com.marshmallow.beehive;

import com.marshmallow.beehive.models.CareerPointModel;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Vector;

/**
 * Created by George on 5/18/2018.
 */
public class CareerPointModelUnitTest {
    private CareerPointModel careerPointModel = new CareerPointModel();

    @Test
    public void testConstruction() {
        assertThat(careerPointModel.getCareerPositionModels(), instanceOf(Vector.class));
    }

    @Test
    public void testName() {
        String name = "testName";
        careerPointModel.setName(name);
        assertEquals(careerPointModel.getName(), name);
    }

    @Test
    public void testLocation() {
        String location = "testLocation";
        careerPointModel.setLocation(location);
        assertEquals(careerPointModel.getLocation(), location);
    }

    @Test
    public void testStartEndDate() {
        String startDate = "startDate";
        String endDate = "endDate";
        careerPointModel.setStartDate(startDate);
        careerPointModel.setEndDate(endDate);

        assertEquals(careerPointModel.getStartDate(), startDate);
        assertEquals(careerPointModel.getEndDate(), endDate);
    }
}
