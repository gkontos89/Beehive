package com.marshmallow.beehive.ui.profileSetup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPointModel;
import com.marshmallow.beehive.models.ModelManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by George on 5/9/2018.
 */
public class CareerPointAdapter extends RecyclerView.Adapter<CareerPointAdapter.CareerPointHolder> {
    private Context context;
    private List<CareerPointModel> careerPointModels;

    public CareerPointAdapter(Context context, List<CareerPointModel> careerPointModels) {
        this.context = context;
        this.careerPointModels = new Vector<>();
        this.careerPointModels = careerPointModels;
    }

    @NonNull
    @Override
    public CareerPointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_setup_career_point_basic, parent, false);
        return new CareerPointHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CareerPointHolder holder, final int position) {
        final CareerPointModel careerPointModel = careerPointModels.get(position);
        holder.getCareerPointName().setText(careerPointModel.getName());
        holder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().getUserModel().getUserStory().getCareerPointModels().remove(careerPointModel);
                notifyDataSetChanged();
            }
        });

        // If the view is selected launch the career point activity and pass the index in the user model
        // to reference
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().setActiveCareerPointModel(careerPointModel);
                Intent intent = new Intent(context.getApplicationContext(), ProfileSetupCareerPointActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return careerPointModels.size();
    }

    public class CareerPointHolder extends RecyclerView.ViewHolder {

        private TextView careerPointName;
        private Button removeButton;

        private CareerPointHolder(View v) {
            super(v);
            careerPointName = v.findViewById(R.id.career_point_text);
            removeButton = v.findViewById(R.id.remove_button);
        }

        // GUI handles
        public TextView getCareerPointName() { return careerPointName; }
        public Button getRemoveButton() { return removeButton; }
    }

}
