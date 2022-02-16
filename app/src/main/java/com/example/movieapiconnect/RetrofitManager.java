package com.example.movieapiconnect;

import android.content.Context;

import com.example.movieapiconnect.RawCertificatePinner.RawCertificatePinner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    Context context;

    public RetrofitManager(Context context) {
        this.context = context;
    }

    public Retrofit retrofitManager() {

        RawCertificatePinner rawCertificatePinner = new RawCertificatePinner(context, R.raw.certificate, "mypassword");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder = rawCertificatePinner.pinCertificate(builder);

        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("https://movie-6701f-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
