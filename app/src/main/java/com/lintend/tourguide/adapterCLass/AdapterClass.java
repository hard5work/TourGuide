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

import com.bumptech.glide.Glide;
import com.lintend.tourguide.Information;
import com.lintend.tourguide.InternetCLass;
import com.lintend.tourguide.R;
import com.lintend.tourguide.model.Modules;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.AdapterViewClass> {
    private Context context;
    private List<Modules> modulesList;

    public AdapterClass(Context context, List<Modules> modulesList) {
        this.context = context;
        this.modulesList = modulesList;
    }

    @NonNull
    @Override
    public AdapterViewClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_main_activity , null);
        AdapterViewClass holder = new AdapterViewClass(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewClass adapterViewClass, int i) {
        final int position = adapterViewClass.getAdapterPosition();
        final  Modules modules  = modulesList.get(position);
        Glide.with(context).load(InternetCLass.ServiceTYpe.URL + modules.getPlaceImage())
                .into(adapterViewClass.imageView);

        adapterViewClass.textView.setText(modules.getPlaceName());
        adapterViewClass.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , Information.class);
                intent.putExtra("id",String.valueOf(modules.getId()));
                intent.putExtra("placeName",modules.getPlaceName());
                intent.putExtra("placeImage",modules.getPlaceImage());
                intent.putExtra("accommodation",modules.getAccommodation());
                intent.putExtra("besttime",modules.getBesttiem());
                intent.putExtra("description",modules.getDesc());
                intent.putExtra("transportation",modules.getTransportation());
                intent.putExtra("highlight",modules.getHighlight());
                intent.putExtra("state",String.valueOf(modules.getState()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modulesList.size();
    }

    public class AdapterViewClass extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public AdapterViewClass(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.activity_main_card);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }


}
