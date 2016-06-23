package com.grupo3.gatoencerrado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

public class LaberintosListActivity extends FragmentActivity implements LaberintosListFragment.Callbacks {

    /**
     * Instance variables
     */
    private boolean isWideScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laberintos_frame);

        if (findViewById(R.id.laberinto_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            isWideScreen = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((LaberintosListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.laberintos_list))
                    .setActivateOnItemClick(true);
        }

    }

    /**
     * Callback method from {@link LaberintosListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (isWideScreen) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

            Bundle arguments = new Bundle();
            arguments.putString(LaberintoDetailFragment.ARG_ITEM_ID, id);
            LaberintoDetailFragment fragment = new LaberintoDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.laberinto_detail_container, fragment)
                    .commit();


        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, LaberintoDetailActivity.class);
            detailIntent.putExtra(LaberintoDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }

    }

}
