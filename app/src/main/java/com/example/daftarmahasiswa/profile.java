package com.example.daftarmahasiswa;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profile extends AppCompatActivity {

    private EditText etNama, etNim, etJurusan, etAlamat;
    private SQLietHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi
        etNama = findViewById(R.id.etnama);
        etNim = findViewById(R.id.etnim);
        etJurusan = findViewById(R.id.etjurusan);
        etAlamat = findViewById(R.id.etalamat);
        dbHelper = new SQLietHelper(this);

        // Ambil data terakhir dari database
        Cursor cursor = dbHelper.getDataAll();
        if (cursor.moveToLast()) {  // ambil data terakhir
            etNama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama")));
            etNim.setText(cursor.getString(cursor.getColumnIndexOrThrow("nim")));
            etJurusan.setText(cursor.getString(cursor.getColumnIndexOrThrow("jurusan")));
            etAlamat.setText(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
        }
        cursor.close();

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(profile.this, home.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.nav_profile) {
                    return true;
                } else if (id == R.id.nav_settings) {
                    startActivity(new Intent(profile.this, setting.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }
}
