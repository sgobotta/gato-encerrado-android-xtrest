package com.grupo3.gatoencerrado.juan;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grupo3.gatoencerrado.R;
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
        int idUser = getActivity().getIntent().getExtras().getInt("userId");
        getLaberintosParaUsuario(idUser);
        super.onActivityCreated(savedInstanceState);
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
        ArrayAdapter<Laberinto> adapter = new ArrayAdapter<Laberinto>(getActivity(), android.R.layout.simple_list_item_activated_1, labsAvailable);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String toastString = "Seleccionaste el laberinto: " + parent.getItemAtPosition(position).toString();
                SingleToast.show(getContext(), toastString, Toast.LENGTH_SHORT);

                // Probablemente esta no sea la manera más feliz de hacerlo, pero funciona y no usa constructor con parametros del fragment.
                Laberinto labSelected = (Laberinto) parent.getItemAtPosition(position);
                LabDetailsFragment fragment = new LabDetailsFragment();
                Bundle args = new Bundle();
                args.putInt("idLaberinto", labSelected.getIdLaberinto());
                args.putString("nombreLaberinto", labSelected.getNombreLaberinto());
                args.putString("imagePath", labSelected.getImagePath());
                fragment.setArguments(args);
                LabListActivity activity = (LabListActivity) LabListFragment.this.getActivity();
                activity.replaceFullscreenFragment(fragment, "labDetails");
            }
        });
    }


}
