package com.lintend.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lintend.tourguide.R;
import com.lintend.tourguide.adapterCLass.StateAdapter;
import com.lintend.tourguide.model.Modules;

import java.util.ArrayList;
import java.util.List;

public class States extends AppCompatActivity {
    RecyclerView recyclerView;
    Modules modules;
    List<Modules> modulesList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);
        recyclerView= findViewById(R.id.stateView);
        toolbar = findViewById(R.id.stateToolBar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("States");
        recyclerView.hasFixedSize();
        modulesList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
   /*     modules = new Modules(
                R.drawable.state1,
                "State 1"
        );
        modulesList.add(modules);
        modules = new Modules(
                R.drawable.state2,
                "State 2"
        );
        modulesList.add(modules);
        modules = new Modules(
                R.drawable.state3,
                "State 3"
        );
        modulesList.add(modules);

        modules = new Modules(
                R.drawable.state4,
                "State 4"
        );
        modulesList.add(modules);
        modules = new Modules(
                R.drawable.state5,
                "State 5"
        );
        modulesList.add(modules);
        modules = new Modules(
                R.drawable.state6,
                "State 6"
        );
        modulesList.add(modules);
        modules = new Modules(
                R.drawable.state7,
                "State 7"
        );
        modulesList.add(modules);*/
          modules = new Modules();
        modules.setText("State 1");
        modules.setImages(R.drawable.state1);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 2");
        modules.setImages(R.drawable.state2);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 3");
        modules.setImages(R.drawable.state3);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 4");
        modules.setImages(R.drawable.state4);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 5");
        modules.setImages(R.drawable.state5);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 6");
        modules.setImages(R.drawable.state6);
        modulesList.add(modules);

        modules = new Modules();
        modules.setText("State 7");
        modules.setImages(R.drawable.state7);
        modulesList.add(modules);

        StateAdapter adapter = new StateAdapter(getApplicationContext(), modulesList);
        recyclerView.setAdapter(adapter);

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
