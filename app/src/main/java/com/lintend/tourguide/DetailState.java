package com.lintend.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lintend.tourguide.R;
import com.lintend.tourguide.adapterCLass.AdapterClass;
import com.lintend.tourguide.model.Modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class DetailState extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    TextView stateno, dstateno;
    String getState , getImage;
    Modules modules;
    List<Modules> modulesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_state);
        recyclerView= findViewById(R.id.dstateTourList);
        stateno = findViewById(R.id.dstateno);
        dstateno = findViewById(R.id.dstatenodes);
        imageView = findViewById(R.id.DstateImage);
        Toolbar toolbar = findViewById(R.id.stateDetailToolBar);
        setSupportActionBar(toolbar);


        modulesList  = new ArrayList<>();

        getState = getIntent().getStringExtra("stateno");

        getImage = getIntent().getStringExtra("stateimage");
       /* Glide.with(getApplicationContext())
                .load(getImage)
                .into(imageView);*/
       imageView.setImageResource(parseInt(getImage));
        String state1 = "State " + getState;
        stateno.setText(state1);
        String descp = "Top places to visit in state " + getState + " of Nepal";
        dstateno.setText(descp);
        setTitle(state1);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() , 2));

        getInfo();
    }

    public void getInfo(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST ,
                InternetCLass.ServiceTYpe.URL + "getDatastate.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("test" , response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0 ; i<jsonArray.length() ; i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Modules modules = new Modules();
                        modules.setId(object.getInt("id"));
                        modules.setPlaceName(object.getString("placeName"));
                        modules.setPlaceImage(object.getString("placeImage"));
                        modules.setHighlight(object.getString("highLight"));
                        modules.setBesttiem(object.getString("bestTime"));
                        modules.setAccommodation(object.getString("accommodation"));
                        modules.setTransportation(object.getString("transportation"));
                        modules.setDesc(object.getString("description"));
                        modules.setState(object.getInt("state"));
                        modulesList.add(modules);

                    }

                    AdapterClass adapterClass = new AdapterClass(getApplicationContext(), modulesList);
                    recyclerView.setAdapter(adapterClass);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("state" , getState);
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}
