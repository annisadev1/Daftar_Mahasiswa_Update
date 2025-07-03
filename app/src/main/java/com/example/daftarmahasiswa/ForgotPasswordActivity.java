package com.example.daftarmahasiswa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailInput;
    Button resetPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailInput = findViewById(R.id.emailInput);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Masukkan email!", Toast.LENGTH_SHORT).show();
                } else {
                    // Simulasi pengiriman email reset password
                    Toast.makeText(ForgotPasswordActivity.this, "Link reset telah dikirim ke " + email, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

