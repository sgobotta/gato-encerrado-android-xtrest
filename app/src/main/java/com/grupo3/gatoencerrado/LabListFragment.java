package com.grupo3.gatoencerrado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.grupo3.gatoencerrado.model.Laberinto;
import com.grupo3.gatoencerrado.service.LaberintosService;
import com.grupo3.gatoencerrado.service.LaberintosServiceBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LabListFragment extends Fragment{

    public LabListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLaberintosParaUsuarioActual();

        Button refresh = (Button) getActivity().findViewById(R.id.button_refresh_lab_list);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLaberintosParaUsuarioActual();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void getLaberintosParaUsuarioActual(){
        int idUser = getActivity().getIntent().getExtras().getInt("userId");
        getLaberintosParaUsuario(idUser);
    }

    private void getLaberintosParaUsuario(int id) {
        LaberintosService laberintosService = LaberintosServiceBuilder.createLaberintosService();
        laberintosService.getLaberintosForUser(id, new Callback<List<Laberinto>>() {
            @Override
            public void success(List<Laberinto> labsAvailable, Response response) {
                adaptToList(labsAvailable);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void adaptToList(List<Laberinto> labsAvailable) {
        ListView listView = (ListView) getActivity().findViewById(R.id.list_view_labs);
        AdapterLabList adapter = new AdapterLabList(getActivity(), labsAvailable);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //triggerToast(parent, position);
                triggerFragmentReplace(parent, position);
            }
        });
    }

    private void triggerFragmentReplace(AdapterView<?> parent, int position) {
        // Probablemente esta no sea la manera más feliz de hacerlo, pero funciona y no usa constructor con parametros del fragment.
        // Armo el nuevo fragment
        LabDetailsFragment fragment = new LabDetailsFragment();

        // Armo el bundle con los argumentos a pasarle al fragment
        Bundle args = new Bundle();
        Laberinto labSelected = (Laberinto) parent.getItemAtPosition(position);
        args.putInt("idLaberinto", labSelected.getIdLaberinto());
        args.putString("nombreLaberinto", labSelected.getNombreLaberinto());
        args.putString("imagePath", labSelected.getImagePath());
        fragment.setArguments(args);

        // Reemplazo el fragment por el nuevo
        LabListActivity activity = (LabListActivity) LabListFragment.this.getActivity();
        activity.handleFragmentChange(fragment, "labDetails");
    }

    private void triggerToast(AdapterView<?> parent, int position) {
        // Esto lo use para testear que funcionara el onClick, capaz despues lo borre.
        String toastString = "Seleccionaste el laberinto: " + parent.getItemAtPosition(position).toString();
        SingleToast.show(getContext(), toastString, Toast.LENGTH_SHORT);
    }


}
