package com.lintend.tourguide.adapterCLass;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lintend.tourguide.DetailState;
import com.lintend.tourguide.R;
import com.lintend.tourguide.model.Modules;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateAdapterVIew> {
    private Context context;
    private List<Modules> modulesList;

    public StateAdapter(Context context, List<Modules> modulesList) {
        this.context = context;
        this.modulesList = modulesList;
    }

    @NonNull
    @Override
    public StateAdapterVIew onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_state_view, null);
        StateAdapterVIew holder = new StateAdapterVIew(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StateAdapterVIew stateAdapterVIew, int i) {
        final int pos = i;
        final Modules modules = modulesList.get(i);
        stateAdapterVIew.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailState.class);
                intent.putExtra("stateno" , String.valueOf(stateAdapterVIew.getAdapterPosition() + 1));
                intent.putExtra("stateimage" , String.valueOf(modules.getImages()));
                context.startActivity(intent);
            }
        });

        stateAdapterVIew.imageView.setImageResource(modules.getImages());
      //  stateAdapterVIew.textView.setText(modules.getDesc());
        stateAdapterVIew.textView.setText(modules.getText());
    }

    @Override
    public int getItemCount() {
        return modulesList.size();
    }

    public class StateAdapterVIew extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        TextView textView;
        public StateAdapterVIew(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.stateImage);
            textView = itemView.findViewById(R.id.stateName);
            cardView = itemView.findViewById(R.id.stateCard);
        }
    }
}
