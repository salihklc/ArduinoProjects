package com.example.salih.bookregister;
import android.content.Context;
import java.util.HashMap;
import java.util.ArrayList;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by salih on 12/18/14.
 */

public class Database extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqllite_database";
    private static final String TABLE_NAME = "film_listesi";
    public static String FILM_ADI = "film_adi";
    public static String FILM_ID = "id";
    public static String FILM_YONETMEN = "yonetmen";
    public static String FILM_YAPIM_YILI = "yil";
    public static String FILM_FIYATI = "fiyat";

    public Database(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + FILM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FILM_ADI + " TEXT,"
                + FILM_YONETMEN + " TEXT,"
                + FILM_YAPIM_YILI + " TEXT,"
                + FILM_FIYATI + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void filmSil(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, FILM_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();

    }

    public void filmEkle(String film_adi, String film_yonetmeni,String film_yapim_yili,String film_fiyat) {
        //kitapEkle methodu ise adý üstünde Databese veri eklemek için
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FILM_ADI, film_adi);
        values.put(FILM_YONETMEN, film_yonetmeni);
        values.put(FILM_YAPIM_YILI, film_yapim_yili);
        values.put(FILM_FIYATI, film_fiyat);
        db.insert(TABLE_NAME, null, values);
        db.close(); //Database Baðlantýsýný kapattýk*/
    }

    public HashMap<String, String> filmDetay(int id){
        //Databeseden id si belli olan row u çekmek için.
        //Bu methodda sadece tek row deðerleri alýnýr.

        //HashMap bir çift boyutlu arraydir.anahtar-deðer ikililerini bir arada tutmak için tasarlanmýþtýr.
        //mesala map.put("x","300"); mesala burda anahtar x deðeri 300.

        HashMap<String,String> film = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            film.put(FILM_ADI, cursor.getString(1));
            film.put(FILM_YONETMEN, cursor.getString(2));
            film.put(FILM_YAPIM_YILI, cursor.getString(3));
            film.put(FILM_FIYATI, cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return kitap
        return film;
    }

    public  ArrayList<HashMap<String, String>> filmler(){

        //Bu methodda ise tablodaki tüm deðerleri alýyoruz
        //ArrayList adý üstünde Array lerin listelendiði bir Array.Burda hashmapleri listeleyeceðiz
        //Herbir satýrý deðer ve value ile hashmap a atýyoruz. Her bir satýr 1 tane hashmap arrayý demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atýp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> filmlist = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                filmlist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return film liste
        return filmlist;
    }

    public void filmDuzenle(String film_adi, String film_yonetmeni,String film_yapim_yili,String film_fiyat,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(FILM_ADI, film_adi);
        values.put(FILM_YONETMEN, film_yonetmeni);
        values.put(FILM_YAPIM_YILI, film_yapim_yili);
        values.put(FILM_FIYATI, film_fiyat);

        // updating row
        db.update(TABLE_NAME, values, FILM_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int getRowCount() {
        // Bu method bu uygulamada kullanýlmýyor ama her zaman lazým olabilir.Tablodaki row sayýsýný geri döner.
        //Login uygulamasýnda kullanacaðýz
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }


    public void resetTables(){
        //Bunuda uygulamada kullanmýyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
