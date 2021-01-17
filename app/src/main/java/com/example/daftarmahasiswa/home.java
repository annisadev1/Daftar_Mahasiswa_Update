package com.example.daftarmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    private Button buttontambah;
    private ListView list_view;
    private ListViewAdapter adapter;
    private ArrayList<dataMahasiswa> listMahasiswa = new ArrayList<>();
    private SQLietHelper helper;
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttontambah = findViewById(R.id.buttontambah);
        list_view = findViewById(R.id.list_view);
        refresh = findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menampilkanData();
            }
        });

        helper = new SQLietHelper(this);

        buttontambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, tambah.class));

            }
        });
        menampilkanData();
    }

    private void menampilkanData(){
        listMahasiswa.clear();
        Cursor res = helper.getDataAll();
        refresh.setRefreshing(true);
        while (res.moveToNext()){
            String id = res.getString(0);
            String nama = res.getString(1);
            String nim = res.getString(2);
            String jurusan = res.getString(3);
            String alamat = res.getString(4);

            listMahasiswa.add(new dataMahasiswa(id, nama, nim, jurusan, alamat));
        }
        adapter = new ListViewAdapter(listMahasiswa, home.this);
        list_view.setAdapter(adapter);
        refresh.setRefreshing(false);
    }
}
