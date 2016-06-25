package com.grupo3.gatoencerrado;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grupo3.gatoencerrado.service.DownloadImageTask;
import com.grupo3.gatoencerrado.service.LaberintosServiceBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LabDetailsFragment extends Fragment {

    private int idLaberinto;

    public LabDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Busca el path de la imagen y la baja con una AsyncTask para no colgar el thread principal
        String path = getArguments().getString("imagePath");
        new DownloadImageTask((ImageView) getActivity().findViewById(R.id.lab_image))
                .execute(LaberintosServiceBuilder.API_URL + "/" + path);

        // Actualiza el texto del fragment
        TextView labName = (TextView) getActivity().findViewById(R.id.details_lab_name);
        labName.setText(getArguments().getString("nombreLaberinto"));

        // Arma el boton para ver el inventario
        idLaberinto = getArguments().getInt("idLaberinto");
        Button verInventario = (Button) getActivity().findViewById(R.id.button_ver_inventario);
        verInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerFragmentChange();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void triggerFragmentChange() {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putInt("idLaberinto", idLaberinto);
        fragment.setArguments(args);
        LabListActivity activity = (LabListActivity) LabDetailsFragment.this.getActivity();
        activity.handleFragmentChange(fragment, "labInventory");
    }
}
