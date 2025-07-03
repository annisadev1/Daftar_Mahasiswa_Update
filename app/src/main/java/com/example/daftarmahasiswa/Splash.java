package com.example.daftarmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Jalankan splash screen selama 3 detik, lalu lanjut ke halaman Login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToLogin = new Intent(Splash.this, Login.class);
                startActivity(goToLogin);
                finish(); // Supaya tidak bisa kembali ke splash screen dengan tombol Back
            }
        }, 3000); // 3 detik
    }
}
