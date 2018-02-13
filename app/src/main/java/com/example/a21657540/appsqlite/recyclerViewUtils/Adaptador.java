package com.example.a21657540.appsqlite.recyclerViewUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21657540.appsqlite.R;
import com.example.a21657540.appsqlite.model.Contacto;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 21657540 on 12/02/2018.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.VHcontacto>{

    private ArrayList<Contacto> misContactos;


    public Adaptador(ArrayList<Contacto> misContactos) {
        this.misContactos = misContactos;
    }

    //creamos la clase interna
    public static class VHcontacto extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvEmail;

        public VHcontacto(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail  = itemView.findViewById(R.id.tvMail);
        }

        public TextView getTvNombre() {
            return tvNombre;
        }

        public TextView getTvEmail() {
            return tvEmail;
        }
    }





    @Override
    public VHcontacto onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        VHcontacto vhc = new VHcontacto(v);
        return vhc;
    }

    @Override
    public void onBindViewHolder(VHcontacto holder, int position) {
        holder.tvNombre.setText(misContactos.get(position).getNombre());
        holder.tvEmail.setText(misContactos.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return misContactos.size();
    }
}
