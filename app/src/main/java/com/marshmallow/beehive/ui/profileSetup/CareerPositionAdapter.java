package com.marshmallow.beehive.ui.profileSetup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.CareerPositionModel;
import com.marshmallow.beehive.models.ModelManager;

import java.util.List;
import java.util.Vector;

/**
 * Created by George on 5/12/2018.
 */
public class CareerPositionAdapter extends RecyclerView.Adapter<CareerPositionAdapter.CareerPositionHolder> {

    private Context context;
    private List<CareerPositionModel> careerPositionModels;

    public CareerPositionAdapter(Context context, List<CareerPositionModel> careerPositionModels) {
        this.context = context;
        this.careerPositionModels = new Vector<>();
        this.careerPositionModels = careerPositionModels;
    }

    @NonNull
    @Override
    public CareerPositionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_setup_career_position_basic, parent, false);
        return new CareerPositionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CareerPositionHolder holder, final int position) {
        final CareerPositionModel careerPositionModel = careerPositionModels.get(position);
        holder.getPositionName().setText(careerPositionModel.getPositionName());
        holder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().getActiveCareerPointModel().getCareerPositionModels().remove(careerPositionModel);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().setActiveCareerPointPositionModel(careerPositionModel);
                Intent intent = new Intent(context.getApplicationContext(), ProfileSetupCareerPositionActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return careerPositionModels.size(); }

    public class CareerPositionHolder extends RecyclerView.ViewHolder {

        private TextView positionName;
        private Button removeButton;

        private CareerPositionHolder(View v) {
            super(v);
            positionName = v.findViewById(R.id.career_position_text);
            removeButton = v.findViewById(R.id.remove_button);
        }

        // GUI handles
        public TextView getPositionName() { return positionName; }
        public Button getRemoveButton() { return removeButton; }
    }

}
