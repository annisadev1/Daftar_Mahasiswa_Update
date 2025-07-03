package com.example.daftarmahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLietHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "akademik.db";
    private static final String TABLE_NAME = "mahasiswa";

    // Kolom
    private static final String COL_ID = "id";
    private static final String COL_NAMA = "nama";
    private static final String COL_NIM = "nim";
    private static final String COL_JURUSAN = "jurusan";
    private static final String COL_ALAMAT = "alamat";

    public SQLietHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAMA + " TEXT, " +
                COL_NIM + " TEXT, " +
                COL_JURUSAN + " TEXT, " +
                COL_ALAMAT + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String nama, String nim, String jurusan, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAMA, nama);
        values.put(COL_NIM, nim);
        values.put(COL_JURUSAN, jurusan);
        values.put(COL_ALAMAT, alamat);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor getDataAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateData(String id, String nama, String nim, String jurusan, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAMA, nama);
        values.put(COL_NIM, nim);
        values.put(COL_JURUSAN, jurusan);
        values.put(COL_ALAMAT, alamat);
        int result = db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{id});
        return result > 0;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{id});
    }
}
