package com.grupo3.gatoencerrado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class LabListActivity extends AppCompatActivity {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Gato Encerrado");
        isTablet = false;

        if(savedInstanceState == null){

            // Adds the first fullscreen fragment if there is no saved instance
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            LabListFragment fragment = new LabListFragment();

            if(findViewById(R.id.content_fragment_lab) == null) {
                fragmentTransaction.add(R.id.fragment_placeholder1, fragment, "labList");
            } else {
                isTablet = true;
                fragmentTransaction.add(R.id.lab_list_placeholder, fragment, "labList");

            }

            fragmentTransaction.commit();
        }
    }

    // replaces the fullscreen fragment for another one
    public void handleFragmentChange(Fragment fragment, String name){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(!isTablet) {
            fragmentTransaction.replace(R.id.fragment_placeholder1, fragment, name);
        } else {
            fragmentTransaction.replace(R.id.content_fragment_lab, fragment, name);
        }
        fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        // Catch back action and pops from backstack
        // (if you called previously to addToBackStack() in your transaction)
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }
        // Default action on back pressed
        else super.onBackPressed();
    }
}
