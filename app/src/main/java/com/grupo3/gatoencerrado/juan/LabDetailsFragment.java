package com.grupo3.gatoencerrado.juan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.grupo3.gatoencerrado.R;
import com.grupo3.gatoencerrado.model.Laberinto;

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
        TextView labName = (TextView) getActivity().findViewById(R.id.details_lab_name);
        TextView labId = (TextView) getActivity().findViewById(R.id.details_lab_id);
        TextView labPath = (TextView) getActivity().findViewById(R.id.details_lab_path);

        idLaberinto = getArguments().getInt("idLaberinto");

        labName.setText(getArguments().getString("nombreLaberinto"));
        labId.setText(String.valueOf(idLaberinto));
        labPath.setText(getArguments().getString("imagePath"));

        Button verInventario = (Button) getActivity().findViewById(R.id.button_ver_inventario);
        verInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryFragment fragment = new InventoryFragment();
                Bundle args = new Bundle();
                args.putInt("idLaberinto", idLaberinto);
                fragment.setArguments(args);
                LabListActivity activity = (LabListActivity) LabDetailsFragment.this.getActivity();
                activity.replaceFullscreenFragment(fragment,"labInventory");
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
