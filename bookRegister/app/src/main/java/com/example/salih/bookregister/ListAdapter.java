package com.example.salih.bookregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by salih on 18.12.2014.
 */
public class ListAdapter extends BaseAdapter {

    Context c;
    ArrayList<HashMap<String,String>> film_liste;

    ListAdapter (Context c, ArrayList<HashMap<String,String>> film_liste){
        this.c=c;
        this.film_liste=film_liste;
    }
    @Override
    public int getCount() {
        return film_liste.size();
    }

    @Override
    public Object getItem(int position) {
        return film_liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder myholder = null;

        if(row==null){
            LayoutInflater ınflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            row=ınflater.inflate(R.layout.list_item,parent,false);
            myholder = new Holder(row);
            row.setTag(myholder);
        }
        else {
            myholder= (Holder) row.getTag();

        }

        myholder.film_adi.setText(film_liste.get(position).get(Database.FILM_ADI));
        myholder.film_fiyati.setText(film_liste.get(position).get(Database.FILM_FIYATI));

        return row;
    }

    class Holder {
        TextView film_adi;
        TextView film_fiyati;

        Holder(View v){
            this.film_adi= (TextView) v.findViewById(R.id.filmAdi);
            this.film_fiyati= (TextView) v.findViewById(R.id.textView4);

        }
    }
}
