package com.grupo3.gatoencerrado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo3.gatoencerrado.model.Laberinto;
import com.grupo3.gatoencerrado.service.LaberintosService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A fragment representing a single Libro detail screen.
 * This fragment is either contained in a {@link LaberintosListActivity}
 * in two-pane mode (on tablets) or a {@link LaberintoDetailActivity}
 * on handsets.
 */
public class LaberintoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LaberintoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String itemID = getArguments().getString(ARG_ITEM_ID);
            obtenerLaberintos(itemID);
        }
    }


    private void obtenerLaberintos(String laberintoId) {
        LaberintosService laberintosService = createLaberintosService();
        laberintosService.getLaberinto(laberintoId, new Callback<Laberinto>() {
            @Override
            public void success(Laberinto laberinto, Response response) {
                mostrarLaberinto(laberinto);
    }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
     }


    private void mostrarLaberinto(Laberinto laberinto) {
        ((TextView) this.getView().findViewById(R.id.labTitulo)).setText(laberinto.getNombre());
    }


     private LaberintosService createLaberintosService() {

     //String SERVER_IP = "10.0.2.2"; esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
     // 10.0.3.15
     String SERVER_IP = "186.139.17.98";
     String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
     String API_URL = "http://"+ SERVER_IP +":9001";

     RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
     LaberintosService laberintosService = restAdapter.create(LaberintosService.class);
     return laberintosService;
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_laberinto_detail, container, false);
        return rootView;
    }

}
