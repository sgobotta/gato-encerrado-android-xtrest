package com.grupo3.gatoencerrado;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.grupo3.gatoencerrado.model.Laberinto;

import java.util.List;

/**
 * Created by santiago on 6/14/16.
 */
public class LaberintoAdapter extends AbstractListAdapter<Laberinto> {

    public LaberintoAdapter(Context context, List<Laberinto> laberintos) {
        super(context, laberintos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Laberinto libro = (Laberinto) getItem(position);
        View row = generateRow(R.layout.laberinto_row, parent);
        setColumnTextView(row, R.id.labTitulo, libro.getNombre());
        return row;
    }

}
