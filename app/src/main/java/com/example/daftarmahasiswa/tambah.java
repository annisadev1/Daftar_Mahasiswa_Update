package com.example.daftarmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private String pilih = "Tambah";
    private String id, nama, nim, jurusan, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etnama = findViewById(R.id.etnama);
        etnim = findViewById(R.id.etnim);
        etjurusan = findViewById(R.id.etjurusan);
        etalamat = findViewById(R.id.etalamat);
        btntambah = findViewById(R.id.btntambah);

        helper = new SQLietHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            getSupportActionBar().setTitle("Ubah Data");
            id = bundle.getString("id");
            nama = bundle.getString("nama");
            nim = bundle.getString("nim");
            jurusan = bundle.getString("jurusan");
            alamat = bundle.getString("alamat");
            pilih = bundle.getString("tanda");

            etnama.setText(nama);
            etnim.setText(nim);
            etjurusan.setText(jurusan);
            etalamat.setText(alamat);
        } else {
            getSupportActionBar().setTitle("tambah data");
        }

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etnama.getText().toString();
                String nim = etnim.getText().toString();
                String jurusan = etjurusan.getText().toString();
                String alamat = etalamat.getText().toString();

                if (TextUtils.isEmpty(nama)){
                    etnama.setError("Data tidak boleh kosong");
                    etnama.requestFocus();
                } else if(TextUtils.isEmpty(nim)){
                    etnim.setError("Data tidak boleh kong");
                    etnim.requestFocus();
                } else if(TextUtils.isEmpty(jurusan)){
                    etjurusan.setError("Data tidak boleh kong");
                    etjurusan.requestFocus();
                } else if(TextUtils.isEmpty(alamat)){
                    etalamat.setError("Data tidak boleh kong");
                    etalamat.requestFocus();
                } else {
                    if (pilih.equals("Tambah")){
                        boolean isInsert = helper.insertData(nama, nim, jurusan, alamat);
                        if (isInsert){
                            Toast.makeText(tambah.this, "data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(tambah.this, home.class));
                            finish();
                        } else {
                            Toast.makeText(tambah.this, "data gagal disimpan", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(tambah.this, home.class));
                            finish();
                        }
                    }else{
                        boolean isUpdate = helper.updateData(
                                id,
                                nama,
                                nim,
                                jurusan,
                                alamat
                        );
                        if (isUpdate){
                            Toast.makeText(tambah.this, "data berhasil diubah", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(tambah.this, home.class));
                            finish();
                        } else {
                            Toast.makeText(tambah.this, "data gagal diubah", Toast.LENGTH_SHORT).show();
                            kosong();
                            startActivity(new Intent(tambah.this, home.class));
                            finish();
                        }

                    }
                }
            }
        });
    }
    private void kosong(){
        etnama.setText(null);
        etnim.setText(null);
        etjurusan.setText(null);
        etalamat.setText(null);
    }
}
