package com.grupo3.gatoencerrado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.grupo3.gatoencerrado.model.Elemento;
import com.grupo3.gatoencerrado.service.LaberintosService;
import com.grupo3.gatoencerrado.service.LaberintosServiceBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {


    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final int idUser = getActivity().getIntent().getExtras().getInt("userId");
        final int idLab = getArguments().getInt("idLaberinto");
        getInventory(idUser, idLab);

        Button actualizarInventario = (Button) getActivity().findViewById(R.id.refresh_inventory);
        actualizarInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryFragment.this.getInventory(idUser, idLab);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void getInventory(int idUser, int idLab){
        LaberintosService laberintosService = LaberintosServiceBuilder.createLaberintosService();
        laberintosService.getInventoryForUser(idUser, idLab, new Callback<List<Elemento>>() {
            @Override
            public void success(List<Elemento> inventario, Response response) {
                adaptToList(inventario);
            }

            @Override
            public void failure(RetrofitError error) {
                // tendria que cambiar el server para que no devuelva una lista vacia, sino un badRequest, si el inventario que se pide no es de un juego en progreso.
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void adaptToList(List<Elemento> inventario) {
        ListView listView = (ListView) getActivity().findViewById(R.id.inventory_list_view);
        ArrayAdapter<Elemento> adapter = new ArrayAdapter<Elemento>(getActivity(), android.R.layout.simple_list_item_activated_1, inventario);
        listView.setAdapter(adapter);
    }
}
