package com.marshmallow.beehive.ui.profileSetup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marshmallow.beehive.R;
import com.marshmallow.beehive.models.ModelManager;

import java.util.List;
import java.util.Vector;

/**
 * Created by George on 5/12/2018.
 */
public class PursuitsAdapter extends RecyclerView.Adapter<PursuitsAdapter.PursuitsHolder> {

    private List<String> pursuits;

    public PursuitsAdapter(List<String> pursuits) {
        this.pursuits = new Vector<>();
        this.pursuits = pursuits;
    }

    @NonNull
    @Override
    public PursuitsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_setup_pursuit, parent, false);
        return new PursuitsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PursuitsHolder holder, final int position) {
        final String pursuit = pursuits.get(position);
        holder.getPursuitName().setText(pursuit);
        holder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelManager.getInstance().getUserModel().getUserStory().getPursuits().remove(pursuit);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pursuits.size();
    }


    public class PursuitsHolder extends RecyclerView.ViewHolder {

        private TextView pursuitName;
        private Button removeButton;

        private PursuitsHolder(View v) {
            super(v);
            pursuitName = v.findViewById(R.id.pursuits_name);
            removeButton = v.findViewById(R.id.remove_button);
        }

        // GUI handles
        public TextView getPursuitName() { return pursuitName; }
        public Button getRemoveButton() { return removeButton; }
    }
}
