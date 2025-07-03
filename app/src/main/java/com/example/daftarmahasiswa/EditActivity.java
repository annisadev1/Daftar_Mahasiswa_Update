package com.example.daftarmahasiswa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditActivity extends AppCompatActivity {

    private EditText etNama, etNim, etJurusan, etAlamat;
    private Button btnUpdate;
    private SQLietHelper helper;
    private String idMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNama = findViewById(R.id.etnama);
        etNim = findViewById(R.id.etnim);
        etJurusan = findViewById(R.id.etjurusan);
        etAlamat = findViewById(R.id.etalamat);
        btnUpdate = findViewById(R.id.btnUpdate);

        helper = new SQLietHelper(this);

        Intent intent = getIntent();
        idMahasiswa = intent.getStringExtra("id");

        if (idMahasiswa == null || idMahasiswa.isEmpty()) {
            Toast.makeText(this, "ID Mahasiswa tidak ditemukan!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etNama.setText(intent.getStringExtra("nama"));
        etNim.setText(intent.getStringExtra("nim"));
        etJurusan.setText(intent.getStringExtra("jurusan"));
        etAlamat.setText(intent.getStringExtra("alamat"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMahasiswa();
            }
        });

        setupBottomNavigation();
    }

    private void updateMahasiswa() {
        String newNama = etNama.getText().toString().trim();
        String newNim = etNim.getText().toString().trim();
        String newJurusan = etJurusan.getText().toString().trim();
        String newAlamat = etAlamat.getText().toString().trim();

        if (newNama.isEmpty() || newNim.isEmpty() || newJurusan.isEmpty() || newAlamat.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean result = helper.updateData(idMahasiswa, newNama, newNim, newJurusan, newAlamat);
        if (result) {
            Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Gagal mengupdate data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(EditActivity.this, home.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(EditActivity.this, profile.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.nav_settings) {
                    return true;
                }
                return false;
            }
        });
    }
}