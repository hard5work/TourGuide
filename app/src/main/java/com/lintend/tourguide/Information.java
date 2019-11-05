package com.lintend.tourguide;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lintend.tourguide.model.Modules;
import com.lintend.tourguide.sessionManager.DatabaseHandler;
import com.lintend.tourguide.sessionManager.SessionManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Information extends AppCompatActivity {
    SessionManager sm;
    Modules modules;
    AdView adView, adVIewBottom;
    Modules modes;
    DatabaseHandler db;
    CardView cardViewDetail;
    List<Modules> modulesList;
    FloatingActionButton fab;
    ImageView placeImageVIew;
    TextView state, desc, accom, transprot, highlight, destination, bestime, stateCOn, descCOn, accoCOn;
    TextView transCOn, highLigCon, destiCOn, bestTImeCon;
    String getDesc,getId, getAccom, getDesti, getState, getImage, getTrans, getHight, getBestime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(getApplicationContext());
        placeImageVIew = findViewById(R.id.imageViewInformation);
        stateCOn = findViewById(R.id.stateView);
        descCOn = findViewById(R.id.descriptionCOntent);
        accoCOn = findViewById(R.id.accommodationContent);
        transCOn = findViewById(R.id.transportationCOntent);
        highLigCon = findViewById(R.id.highlightContent);
        destiCOn = findViewById(R.id.place);
        bestTImeCon = findViewById(R.id.bestimeContent);
        cardViewDetail = findViewById(R.id.detail_card);

        getId = getIntent().getStringExtra("id");
        //Toast.makeText(this, getId, Toast.LENGTH_SHORT).show();
        getDesc = getIntent().getStringExtra("description");
        getAccom = getIntent().getStringExtra("accommodation");
        getDesti =getIntent().getStringExtra("placeName");
        getState = getIntent().getStringExtra("state");
        getImage = getIntent().getStringExtra("placeImage");
        getTrans = getIntent().getStringExtra("transportation");
        getHight = getIntent().getStringExtra("highlight");
        getBestime = getIntent().getStringExtra("besttime");


        adView = findViewById(R.id.detailAD);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("33BE22550B43518CCDA7DE42D04EE231").build();
        adView.loadAd(adRequest);
        adVIewBottom = findViewById(R.id.detailADbottom);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequestBottom = new AdRequest.Builder().addTestDevice("33BE22550B43518CCDA7DE42D04EE231").build();
        adVIewBottom.loadAd(adRequestBottom);




        /*
        *   getDesc, getAccom, getDesti, getState, getImage, getTrans, getHight, getBestime
        *   */

        Glide.with(getApplicationContext()).load(InternetCLass.ServiceTYpe.URL + getImage)
                .into(placeImageVIew);
        String stateno  = "State " + getState;
        stateCOn.setText(stateno);
        descCOn.setText(getDesc);
        accoCOn.setText(getAccom);
        transCOn.setText(getTrans);
        highLigCon.setText(getHight);
        destiCOn.setText(getDesti);
        bestTImeCon.setText(getBestime);
        setTitle(getDesti);
        sm = new SessionManager(getApplicationContext());
        if (isNetworkAvailable()){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
             lp.setMargins(0,0,0, 10);
            cardViewDetail.setLayoutParams(lp);

        }



        fab = findViewById(R.id.fab);

        modulesList = new ArrayList<>();
        modules = new Modules(
                parseInt(getId),
                getDesti,
                getImage,
                getDesc,
                getHight,
                getAccom,
                getTrans,
                getBestime,
                parseInt(getState)
        );
        modulesList.add(modules);

        modes = new Modules();
        modes.setId(parseInt(getId));
        modes.setDesc(getDesc);
        modes.setPlaceName(getDesti);
        modes.setPlaceImage(getImage);
        modes.setHighlight(getHight);
        modes.setAccommodation(getAccom);
        modes.setBesttiem(getBestime);
        modes.setTransportation(getTrans);
        modes.setState(parseInt(getState));
        modulesList.add(modes);


        fabTOggle();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Information.this, getId, Toast.LENGTH_SHORT).show();
                if (db.isFavoritesExist(modules.getId()))
                {
                    db.deleteFavorites(modules);
                }
                else {
                    db.addOneFavorite(modules);
                }
                fabTOggle();

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void fabTOggle(){
        if (db.isFavoritesExist(modules.getId())){
            fab.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else
        {
            fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }



  /*  public boolean checkFavoriteItem(String checkProduct , String name, String image) {
        boolean check = false;
        ArrayList<String> favorites =  sm.getFavorites();
        ArrayList<String> namel, imagel;
      //  namel = sm.getnameFavorites();
      //  imagel = sm.getimageFavorites();
        if (favorites != null) {
            for (String product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }

        if (namel != null) {
            for (String product : namel) {
                if (product.equals(name)) {
                    check = true;
                    break;
                }
            }
        }

        if (imagel != null) {
            for (String product : imagel) {
                if (product.equals(image)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }*/
}
