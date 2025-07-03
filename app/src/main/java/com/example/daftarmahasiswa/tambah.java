package com.example.daftarmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah extends AppCompatActivity {

    private EditText etnama, etnim, etjurusan, etalamat;
    private Button btntambah;
    private SQLietHelper helper;
    private String mode = "Tambah"; // Default
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inisialisasi
        etnama = findViewById(R.id.etnama);
        etnim = findViewById(R.id.etnim);
        etjurusan = findViewById(R.id.etjurusan);
        etalamat = findViewById(R.id.etalamat);
        btntambah = findViewById(R.id.btntambah);
        helper = new SQLietHelper(this);

        // Ambil data intent (jika ada)
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getSupportActionBar().setTitle("Ubah Data");
            id = bundle.getString("id");
            etnama.setText(bundle.getString("nama"));
            etnim.setText(bundle.getString("nim"));
            etjurusan.setText(bundle.getString("jurusan"));
            etalamat.setText(bundle.getString("alamat"));
            mode = bundle.getString("tanda"); // "Ubah"
        } else {
            getSupportActionBar().setTitle("Tambah Data");
        }

        // Tombol tambah/ubah
        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etnama.getText().toString().trim();
                String nim = etnim.getText().toString().trim();
                String jurusan = etjurusan.getText().toString().trim();
                String alamat = etalamat.getText().toString().trim();

                if (TextUtils.isEmpty(nama)) {
                    etnama.setError("Nama tidak boleh kosong");
                    etnama.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(nim)) {
                    etnim.setError("NIM tidak boleh kosong");
                    etnim.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(jurusan)) {
                    etjurusan.setError("Jurusan tidak boleh kosong");
                    etjurusan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(alamat)) {
                    etalamat.setError("Alamat tidak boleh kosong");
                    etalamat.requestFocus();
                    return;
                }

                boolean sukses;
                if (mode.equals("Tambah")) {
                    sukses = helper.insertData(nama, nim, jurusan, alamat);
                } else {
                    sukses = helper.updateData(id, nama, nim, jurusan, alamat);
                }

                if (sukses) {
                    simpanKeSharedPref(nama, nim, jurusan, alamat);
                    Toast.makeText(tambah.this,
                            mode.equals("Tambah") ? "Data berhasil disimpan" : "Data berhasil diubah",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tambah.this,
                            mode.equals("Tambah") ? "Data gagal disimpan" : "Data gagal diubah",
                            Toast.LENGTH_SHORT).show();
                }

                kosong();
                startActivity(new Intent(tambah.this, home.class));
                finish();
            }
        });
    }

    private void simpanKeSharedPref(String nama, String nim, String jurusan, String alamat) {
        SharedPreferences pref = getSharedPreferences("mahasiswa_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("nama", nama);
        editor.putString("nim", nim);
        editor.putString("jurusan", jurusan);
        editor.putString("alamat", alamat);
        editor.apply();
    }

    private void kosong() {
        etnama.setText("");
        etnim.setText("");
        etjurusan.setText("");
        etalamat.setText("");
    }
}

