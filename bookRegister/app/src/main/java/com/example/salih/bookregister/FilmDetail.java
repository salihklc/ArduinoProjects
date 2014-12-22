package com.example.salih.bookregister;

import java.util.HashMap;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by salih on 12/7/14.
 */
public class FilmDetail extends ActionBarActivity {

    Button filmDuzenle,filmSil;
    EditText filmAdi,filmYonetmeni,filmTarihi,filmFiyat;
    String adi,yonetmeni,tarihi,fiyati;
    int id;
    protected void onCreate(Bundle savedInstanteState){

        super.onCreate(savedInstanteState);
        setContentView(R.layout.detail_film);

        Intent myi = getIntent();
        String temp=myi.getStringExtra("id");
        id=Integer.parseInt(temp);

        Database myDb = new Database(getApplicationContext());
        HashMap<String, String> map = myDb.filmDetay(id);

        filmAdi.setText(map.get("film_adi").toString());
        filmYonetmeni.setText(map.get("yonetmen").toString());
        filmTarihi.setText(map.get("yil").toString());
        filmFiyat.setText(map.get("fiyat").toString());


        filmDuzenle =   (Button)    findViewById(R.id.editFilmButton);
        filmSil     =   (Button)    findViewById(R.id.deleteFilmButton);
        filmAdi     =   (EditText)    findViewById(R.id.filmName);
        filmYonetmeni   =   (EditText)  findViewById(R.id.filmWriter);
        filmTarihi  =   (EditText)  findViewById(R.id.filmDate);
        filmFiyat   =   (EditText)  findViewById(R.id.filmPrice);
    }

    public void editFilm(View v){

        adi=filmAdi.getText().toString();
        yonetmeni=filmYonetmeni.getText().toString();
        tarihi=filmTarihi.getText().toString();
        fiyati=filmFiyat.getText().toString();

        Database myDb = new Database(getApplicationContext());
        myDb.filmDuzenle(adi, yonetmeni, tarihi, fiyati, id);
        Toast.makeText(getApplicationContext(), "Başarıyla Güncellendi", Toast.LENGTH_LONG).show();

    }

    public void deleteFilm(View v){

        Database myDb = new Database(getApplicationContext());
        myDb.filmSil(id);
        Toast.makeText(getApplicationContext(), "Basarıyla Silindi", Toast.LENGTH_LONG).show();

    }

}
