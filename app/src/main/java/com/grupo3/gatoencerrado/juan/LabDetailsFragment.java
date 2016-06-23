package com.grupo3.gatoencerrado.juan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo3.gatoencerrado.R;
import com.grupo3.gatoencerrado.model.Laberinto;

/**
 * A simple {@link Fragment} subclass.
 */
public class LabDetailsFragment extends Fragment {


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

        labName.setText(getArguments().getString("nombreLaberinto"));
        labId.setText(String.valueOf(getArguments().getInt("idLaberinto")));
        labPath.setText(getArguments().getString("imagePath"));

        super.onActivityCreated(savedInstanceState);
    }
}
