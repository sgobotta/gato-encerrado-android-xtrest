package com.grupo3.gatoencerrado.juan;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.grupo3.gatoencerrado.R;
import com.grupo3.gatoencerrado.model.Laberinto;
import com.grupo3.gatoencerrado.model.User;
import com.grupo3.gatoencerrado.service.LaberintosService;
import com.grupo3.gatoencerrado.service.LaberintosServiceBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LabListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LabListFragment fragment = new LabListFragment();

        fragmentTransaction.add(R.id.fragment_placeholder1, fragment);
        fragmentTransaction.commit();
    }


}
