package com.kita.bersama.loginregister.apihelper;

/**
 * Created by user on 4/5/2018.
 */

public class UtilsApi {
    // ini adalah localhost.
    public static final String BASE_URL_API = "http://bekonang-store.000webhostapp.com/api/v1/user/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
