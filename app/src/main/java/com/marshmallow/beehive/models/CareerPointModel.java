package com.marshmallow.beehive.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * This is essentially a point in time spent at a single company.  Cool thing here is we can experiment
 * with sections that haven't been explored yet
 *
 * Created by George on 4/8/2018.
 */
public class CareerPointModel implements BeehiveModel{
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private Vector<CareerPositionModel> careerPositionModels;

    public CareerPointModel() {
    }

    /**
     * Getters and setters
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public Vector<CareerPositionModel> getCareerPositionModels() { return careerPositionModels; }

    /**
     * Parceable functions
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getName());
        out.writeString(getLocation());
        out.writeString(getStartDate());
        out.writeString(getEndDate());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public CareerPointModel createFromParcel(Parcel in) {
            return new CareerPointModel(in);
        }

        public CareerPointModel[] newArray(int size) {
            return new CareerPointModel[size];
        }
    };

    private CareerPointModel(Parcel in) {
        setName(in.readString());
        setLocation(in.readString());
        setStartDate(in.readString());
        setEndDate(in.readString());
//        final int N = in.readInt();
//        for (int i=0; i<N; i++) {
//            getCareerPositionModels().add(in.readParcelable(CareerPositionModel.class.getClassLoader());
//        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
