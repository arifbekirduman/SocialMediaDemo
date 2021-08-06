package com.example.mysocialmedia.api;

public class ApiUtils {
    private ApiUtils(){}

    public static final String BASE_URL = "http://192.168.8.107:8080/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
