package com.kita.bersama.loginregister.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by user on 4/5/2018.
 */

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://localhost/mahasiswa/include/login.php
    @FormUrlEncoded
    @POST("signin")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://localhost/mahasiswa/include/register.php
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("notelp") String notelp,
                                       @Field("password") String password);
}
