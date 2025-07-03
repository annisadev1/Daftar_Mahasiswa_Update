package com.example.daftarmahasiswa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    private Button buttontambah;
    private TextView btnLogout;
    private ListView listView;
    private SwipeRefreshLayout refreshLayout;
    private SQLietHelper helper;
    private ArrayList<dataMahasiswa> listMahasiswa = new ArrayList<>();
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttontambah = findViewById(R.id.buttontambah);
        btnLogout = findViewById(R.id.btnlogout);
        listView = findViewById(R.id.list_view);
        refreshLayout = findViewById(R.id.refresh);
        helper = new SQLietHelper(this);

        adapter = new ListViewAdapter(listMahasiswa, home.this);
        listView.setAdapter(adapter);

        tampilkanDataMahasiswa();

        buttontambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, tambah.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor loginEditor = getSharedPreferences("login_pref", MODE_PRIVATE).edit();
                loginEditor.clear();
                loginEditor.apply();

                SharedPreferences.Editor mahasiswaEditor = getSharedPreferences("mahasiswa_pref", MODE_PRIVATE).edit();
                mahasiswaEditor.clear();
                mahasiswaEditor.apply();

                Toast.makeText(home.this, "Logout berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(home.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilkanDataMahasiswa();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    // Sudah di halaman home
                    return true;
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(home.this, profile.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.nav_settings) {
                    startActivity(new Intent(home.this, setting.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void tampilkanDataMahasiswa() {
        refreshLayout.setRefreshing(true);
        listMahasiswa.clear();
        Cursor res = helper.getDataAll();

        while (res.moveToNext()) {
            String id = res.getString(0);
            String nama = res.getString(1);
            String nim = res.getString(2);
            String jurusan = res.getString(3);
            String alamat = res.getString(4);
            listMahasiswa.add(new dataMahasiswa(id, nama, nim, jurusan, alamat));
        }

        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilkanDataMahasiswa();
    }
}
