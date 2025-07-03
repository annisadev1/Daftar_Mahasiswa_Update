package com.example.daftarmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private TextView register, forgotPassword;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Tidak ada perubahan tampilan

        // Inisialisasi komponen
        btnLogin = findViewById(R.id.btnlogin);
        register = findViewById(R.id.register);
        forgotPassword = findViewById(R.id.forgotPassword);
        etUsername = findViewById(R.id.etUsername);  // Pastikan ID sesuai dengan layout
        etPassword = findViewById(R.id.etPassword);  // Pastikan ID sesuai dengan layout

        // Disable tombol login secara default
        btnLogin.setEnabled(false);

        // TextWatcher untuk cek apakah username dan password sudah terisi
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Cek apakah username dan password sudah terisi
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Enable tombol login hanya jika keduanya sudah terisi
                btnLogin.setEnabled(!username.isEmpty() && !password.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        // Set TextWatcher untuk username dan password
        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        // Login button handler
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Ambil username dan password yang sudah disimpan dari registrasi
                SharedPreferences preferences = getSharedPreferences("login_pref", MODE_PRIVATE);
                String savedUsername = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");

                // Cek apakah username dan password yang dimasukkan cocok
                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    // Simpan status login
                    SharedPreferences.Editor editor = getSharedPreferences("login_pref", MODE_PRIVATE).edit();
                    editor.putBoolean("is_logged_in", true);
                    editor.apply();

                    // Pindah ke Home
                    startActivity(new Intent(Login.this, home.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Register handler
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RegisterActivity.class));
            }
        });

        // Forgot password handler
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPasswordActivity.class));
            }
        });
    }
}
