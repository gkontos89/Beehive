package com.marshmallow.beehive.ui.profileSetup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPointModel;

import java.util.ArrayList;

/**
 * Created by George on 5/9/2018.
 */
public class CareerPointAdapter extends RecyclerView.Adapter<CareerPointAdapter.CareerPointHolder> {
    private ArrayList<CareerPointModel> careerPointModels;

    public CareerPointAdapter(ArrayList<CareerPointModel> careerPointModels) {
        this.careerPointModels = new ArrayList<>();
        this.careerPointModels = careerPointModels;
    }

    @NonNull
    @Override
    public CareerPointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_setup_career_point, parent, false);
        return new CareerPointHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CareerPointHolder holder, int position) {
        CareerPointModel careerPointModel = careerPointModels.get(position);
        holder.getCareerPointName().setText(careerPointModel.getName());
        holder.getLocation().setText(careerPointModel.getLocation());
        holder.getStartDate().setText(careerPointModel.getStartDate());
        holder.getEndDate().setText(careerPointModel.getEndDate());


        // TODO put listeners on text entries to save data?
        // TODO handle career positions
    }

    @Override
    public int getItemCount() {
        return careerPointModels.size();
    }

    public class CareerPointHolder extends RecyclerView.ViewHolder {

        private EditText careerPointName;
        private EditText location;
        private EditText startDate;
        private EditText endDate;
        private Button addCareerPositionButton;
        private RecyclerView careerPositions;


        private CareerPointHolder(View v) {
            super(v);
            careerPointName = v.findViewById(R.id.career_point_text);
            location = v.findViewById(R.id.career_point_location_text);
            startDate = v.findViewById(R.id.start_date_text);
            endDate = v.findViewById(R.id.end_date_text);
            addCareerPositionButton = v.findViewById(R.id.add_career_position_button);
            careerPositions = v.findViewById(R.id.career_positions_rv);
        }

        public EditText getCareerPointName() { return careerPointName; }
        public EditText getLocation() { return location; }
        public EditText getStartDate() { return startDate; }
        public EditText getEndDate() { return endDate; }
        public Button getAddCareerPositionButton() { return addCareerPositionButton; }
        public RecyclerView getCareerPositions() { return careerPositions; }
    }

}
