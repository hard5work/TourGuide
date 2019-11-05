package com.lintend.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lintend.tourguide.adapterCLass.AdapterClass;
import com.lintend.tourguide.model.Modules;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    Modules modules;
    List<Modules> modulesList ;
    FloatingActionButton fab;
    Toolbar toolbar;
    NestedScrollView nsv;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     fab = (FloatingActionButton) findViewById(R.id.fab);
     adView = findViewById(R.id.adID);
     MobileAds.initialize(getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
     AdRequest adRequest = new AdRequest.Builder().addTestDevice("33BE22550B43518CCDA7DE42D04EE231").build();
     adView.loadAd(adRequest);





     nsv = findViewById(R.id.nsv);
        modulesList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() , 2));

      loadData();



       nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
           @Override
           public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
               if (i1 > i3){
                   fab.hide();


               }
               else {
                   fab.show();
               }
           }
       });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       /* //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Destination) {
            Intent intent = new Intent(MainActivity.this , MainActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_Favorite) {
            Intent intent = new Intent(MainActivity.this , FavoritePlace.class);
            startActivity(intent);


        } else if (id == R.id.nav_States) {
            Intent intent = new Intent(MainActivity.this , States.class);
            startActivity(intent);

        } /*else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_rate) {

        } */else if (id == R.id.nav_about) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popup_about, null , false);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();

            TextView ok = view.findViewById(R.id.appOK);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, InternetCLass.ServiceTYpe.URL + "getData.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray  = new JSONArray(response);
                    for (int i = 0 ; i < jsonArray.length() ; i++){
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
                    AdapterClass adapterClass = new AdapterClass(getApplicationContext() , modulesList);
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
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try{
                    Cache.Entry cachEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cachEntry == null){
                        cachEntry = new Cache.Entry();
                    }

                    final long cacheHItButRefreshed = 3*60*1000;
                    final long cacheExpired = 24*60 *60 *1000;
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHItButRefreshed;
                    final long ttl = now + cacheExpired;
                    cachEntry.data = response.data;
                    cachEntry.softTtl = softExpire;
                    cachEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null){
                        cachEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null){
                        cachEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                     cachEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return  Response.success(new String(jsonString), cachEntry);
                }catch (UnsupportedEncodingException e){
                    return  Response.error(new ParseError(e));
                }

            }

            @Override
            protected void deliverResponse(String response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };

        requestQueue.add(stringRequest);
    }
}
