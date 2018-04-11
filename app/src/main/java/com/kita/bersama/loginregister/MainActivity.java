package com.kita.bersama.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvResultNama, tvResultEmail;
    String resultNama, resultEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        // untuk mendapatkan data dari activity sebelumnya, yaitu activity login.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultNama = extras.getString("result_nama");
//            resultEmail = extras.getString("result_email");
        tvResultNama.setText(resultNama);
//        tvResultEmail.setText(resultEmail);
    }

    private void initComponents() {
            tvResultNama = (TextView) findViewById(R.id.tvResultNama);
            tvResultEmail = (TextView) findViewById(R.id.tvResultEmail);

    }
}
