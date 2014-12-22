package com.example.salih.bookregister;
import java.util.HashMap;
import java.util.ArrayList;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {

    String movieName,movieYönet,movieDate,moviePrice;
    Button filmEkle;
    Button filmListele;
    EditText filmAdi;
    EditText filmWriter;
    EditText filmTarih;
    EditText filmFiyat;
    ListView filmList;
    ListView lv;
    ListAdapter adapter;
    ArrayList <HashMap<String, String >> film_liste;
    String film_adlari[];
    int film_idler[];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmEkle        =   (Button)        findViewById(R.id.addFilmButton);
        filmListele     =   (Button)        findViewById(R.id.listFilmButton);
        filmAdi         =   (EditText)      findViewById(R.id.filmName);
        filmWriter      =   (EditText)      findViewById(R.id.filmWriter);
        filmTarih       =   (EditText)      findViewById(R.id.filmDate);
        filmFiyat       =   (EditText)      findViewById(R.id.filmPrice);
        filmList        =   (ListView)      findViewById(R.id.listFilmView);

    }


    public void addFilm(View v){

        movieName=filmAdi.getText().toString();
        movieYönet=filmWriter.getText().toString();
        movieDate=filmTarih.getText().toString();
        moviePrice=filmFiyat.getText().toString();

        Database mydb = new Database(getApplicationContext());
        mydb.filmEkle(movieName,movieYönet, movieDate, moviePrice);
        Toast.makeText(getApplicationContext(), "Filminiz Database'e  Başarı ile Kaydedilmiştir ", Toast.LENGTH_SHORT).show();
        filmAdi.setText("");
        filmWriter.setText("");
        filmFiyat.setText("");
        filmTarih.setText("");

    }

    public void listFilm(View v){

        Database myDb = new Database(getApplicationContext());
        film_liste = myDb.filmler();
        if( film_liste.size()==0)
        {
            Toast.makeText(getApplicationContext(), "Hiç Film Eklenmemiş", Toast.LENGTH_SHORT).show();
        }
        else
        {

            lv = (ListView) findViewById(R.id.listFilmView);
            adapter = new ListAdapter(MainActivity.this,film_liste);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), FilmDetail.class);
                    intent.putExtra("id",film_liste.get(position).get(Database.FILM_ID) );
                    startActivity(intent);
                }
            });


        }

    }
}
