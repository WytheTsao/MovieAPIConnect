package com.example.movieapiconnect.RawCertificatePinner;

import okhttp3.OkHttpClient;

public interface OkHttpCertificatePinner {
    OkHttpClient.Builder pinCertificate(OkHttpClient.Builder okhttpBuilder);
}
