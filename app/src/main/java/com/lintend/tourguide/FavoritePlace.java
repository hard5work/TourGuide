package com.lintend.tourguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lintend.tourguide.model.Modules;
import com.lintend.tourguide.sessionManager.DatabaseHandler;
import com.lintend.tourguide.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class FavoritePlace extends AppCompatActivity {
    RecyclerView favRecyce;
    SessionManager sm;
    DatabaseHandler db;
    Toolbar toolbar;
    List<Modules> idlist,namelist,imagelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_place);
        favRecyce = findViewById(R.id.favoritePlaRec);
        toolbar = findViewById(R.id.favToolbar);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Favorite Places");

        favRecyce.hasFixedSize();
        favRecyce.setLayoutManager(new GridLayoutManager(getApplicationContext() , 2));
        sm = new SessionManager(getApplicationContext());
        db = new DatabaseHandler(getApplicationContext());
        idlist = new ArrayList<>();
        imagelist = new ArrayList<>();
        namelist = new ArrayList<>();


       idlist = db.getAllFavorites();

   /*     idlist= sm.getFavorites();
        imagelist = sm.getimageFavorites();
        namelist = sm.getnameFavorites();
*/
        if(idlist == null ) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.relativeFAV) , "NO fav Items",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        if(idlist.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.relativeFAV) , "NO fav Items",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            FavAdapter adapter = new FavAdapter(getApplicationContext(), idlist);
            favRecyce.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        db.getAllFavorites().clear();
        FavAdapter adapter = new FavAdapter(getApplicationContext(), idlist);
        favRecyce.setAdapter(adapter);

        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavAdapterView>{

    Context context;
    List<Modules> list , namelist, imageList;

    public FavAdapter(Context context, List<Modules> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FavAdapter.FavAdapterView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_favorite_place , null);
        FavAdapterView holder = new FavAdapterView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.FavAdapterView favAdapterView, int i) {
        final int pos =  favAdapterView.getAdapterPosition();
        final Modules modules = list.get(pos);
           favAdapterView.textView.setText(modules.getPlaceName());
         favAdapterView.cardView.setOnClickListener(new View.OnClickListener() {
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
       Glide.with(context).load(InternetCLass.ServiceTYpe.URL + modules.getPlaceImage()).into(favAdapterView.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavAdapterView extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView , textViewID;
        ImageView imageView;
        public FavAdapterView(@NonNull View itemView) {
            super(itemView);
           textView =itemView.findViewById(R.id.favName);
            cardView = itemView.findViewById(R.id.cardFavorite);
            imageView = itemView.findViewById(R.id.favImage);
        }
    }

}
