package com.blogspot.yupocket.yupocket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1 ;
    public static final String DATABASE_NAME = "record.db";
    public static final String TABLE_NAME = "pengeluaran";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_JENIS = "jenis";
    public static final String COLUMN_KETERANGAN = "keterangan";
    public static final String COLUMN_BIAYA = "biaya";
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TANGGAL + " TEXT, " +
            COLUMN_JENIS + " TEXT, " +
            COLUMN_KETERANGAN + " TEXT, " +
            COLUMN_BIAYA + " FLOAT );";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void saveNewRecord(Record record) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TANGGAL, record.getTanggal());
        values.put(COLUMN_JENIS, record.getJenis());
        values.put(COLUMN_KETERANGAN, record.getKeterangan());
        values.put(COLUMN_BIAYA, record.getBiaya());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public List<Record> recordList(){
        String query;
        query = "SELECT  * FROM " + TABLE_NAME;

        List<Record> recordLinkedList = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Record record;

        if (cursor.moveToFirst()){
            do{
                record = new Record();

                record.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                record.setTanggal(cursor.getString(cursor.getColumnIndex(COLUMN_TANGGAL)));
                record.setJenis(cursor.getString(cursor.getColumnIndex(COLUMN_JENIS)));
                record.setKeterangan(cursor.getString(cursor.getColumnIndex(COLUMN_KETERANGAN)));
                record.setBiaya(cursor.getFloat(cursor.getColumnIndex(COLUMN_BIAYA)));

                recordLinkedList.add(record);
            } while (cursor.moveToNext());
        }

        return recordLinkedList;
    }

    public Float getSum() {
        String query;
        query = "SELECT SUM (biaya) FROM " + TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        float total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(0);
        }

        return total;
    }


}
