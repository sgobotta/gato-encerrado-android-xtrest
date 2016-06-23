package com.grupo3.gatoencerrado.juan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getUsersPlaying();
    }

    public void seleccionarUserYLogear(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_user_selection);
        if(spinner.getSelectedItem() != null) {
            User user = (User) spinner.getSelectedItem();
            Intent changeToLabList = new Intent(this, LabListActivity.class);
            changeToLabList.putExtra("userId", user.getID());
            startActivity(changeToLabList);
        } else {
            SingleToast.show(this, "No seleccionaste ningun usuario!", Toast.LENGTH_SHORT);
        }
    }

    public void actualizarLista(View view){
        getUsersPlaying();
    }

    private void getUsersPlaying() {
        LaberintosService laberintosService = LaberintosServiceBuilder.createLaberintosService();
        laberintosService.getUsersPlaying(new Callback<List<User>>() {
            @Override
            public void success(List<User> usersPlaying, Response response) {
                adaptToSpinner(usersPlaying);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void adaptToSpinner(List<User> usersPlaying) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_user_selection);
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, usersPlaying);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(usersPlaying.size() == 0){
            SingleToast.show(this, "No hay usuarios jugando actualmente", Toast.LENGTH_SHORT);
        }
    }


}
