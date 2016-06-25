package com.grupo3.gatoencerrado;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grupo3.gatoencerrado.model.Laberinto;
import com.grupo3.gatoencerrado.service.LaberintosServiceBuilder;

import java.util.List;

public class AdapterLabList extends BaseAdapter {
    private final ImageDownloader imageDownloader = new ImageDownloader();
    private List<Laberinto> listData;
    private LayoutInflater layoutInflater;

    public AdapterLabList(Context context, List<Laberinto> laberintos) {
        this.listData = laberintos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Uso un holder porque dicen que es buena práctica, y permite reusar los convertView
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_row_layout, parent, false);
            holder = new ViewHolder();
            holder.labName = (TextView) convertView.findViewById(R.id.row_text_view);
            holder.labImage = (ImageView) convertView.findViewById(R.id.row_lab_image);
            holder.labCheck = (ImageView) convertView.findViewById(R.id.check_image_playing);
            convertView.setTag(holder);
        } else {
           holder = (ViewHolder) convertView.getTag();
        }

        Laberinto laberinto = listData.get(position);
        String imagePath = LaberintosServiceBuilder.API_URL + "/" + laberinto.getImagePath();

        holder.labName.setText(laberinto.getNombreLaberinto());
        if(laberinto.isPlaying()) {
            holder.labCheck.setImageResource(R.drawable.check_box);
        } else {
            holder.labCheck.setImageResource(android.R.color.transparent);
        }
        // Aca use el imageDownloader (Gracias internet), porque el DownloadImageTask se bugeaba zarpado con la listView,
        // al punto que se quedaba haciendo pedidos Async por 20 sec, y te tildaba todas las descargas de imagenes.
        // Estuve 4 horas intentando solucionarlo, leyendo en stackOverflow, hasta que tiraron esta helper class.
        // De todas maneras la reduje bastante, porque tambien tenia implementado un sistema de cache, que terminaba
        // usando muchisima memoria y que para lo que pesan nuestra img no hacia falta.
        imageDownloader.download(imagePath, holder.labImage);

        return convertView;
    }

}
