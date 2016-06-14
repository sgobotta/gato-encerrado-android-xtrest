package com.grupo3.gatoencerrado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LaberintosFragment extends Fragment {


    /**
     * NO SE ESTA UTILIZANDO DADA LA NUEVA EXIGENCIA DE DOS SCREENS SEGUN DEVICE
     */

    public LaberintosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.laberintos_frame, container, false);
    }

}