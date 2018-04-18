package com.kita.bersama.loginregister;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kita.bersama.loginregister.apihelper.BaseApiService;
import com.kita.bersama.loginregister.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    TextView coba;
    Button btnLogin;
    Button btnRegister;
    ProgressDialog loading;
    String TAG="";
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
    }

    private void initComponents() {
        coba = (TextView) findViewById(R.id.coba);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }

    private void requestLogin() {
            mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (!jsonRESULTS.getString("msg").equals("404")){
                                        // Jika login berhasil maka data nama yang ada di response API
                                        // akan diparsing ke activity selanjutnya.
                                        String success =  jsonRESULTS.getString("msg");
                                        Toast.makeText(mContext, success, Toast.LENGTH_SHORT).show();
                                        String nama = jsonRESULTS.getJSONObject("user").getString("email");
                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("result_nama", nama);
                                        startActivity(intent);
                                        Log.d("hh", "uuuuuu"+nama );
                                    } else {
                                        // Jika login gagal
                                        String error_message = jsonRESULTS.getString("404");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();

                                        Log.d(TAG, "onResponse: "+error_message);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    Toast.makeText(mContext, "Login Gagal", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                            loading.dismiss();
                        }
                    });

    }
}
