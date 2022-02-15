package com.example.movieapiconnect.RawCertificatePinner;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class RawCertificatePinner implements OkHttpCertificatePinner {

    private static final String CERTIFICATE_TYPE = "BKS";
    private static final String DEFAULT_TLS_VERSION = "TLSv1.2";

    private final Context context;
    @RawRes
    private final int certificate;
    private final String certificatePassword;

    public RawCertificatePinner(@NonNull Context context,
                                @RawRes int certificate,
                                @NonNull String certificatePassword) {
        this.context = context;
        this.certificate = certificate;
        this.certificatePassword = certificatePassword;
    }

    @Override
    public OkHttpClient.Builder pinCertificate(OkHttpClient.Builder okhttpBuilder) {
        final KeyStore trustedCertificate = getTrustedCertificate();
        final TrustManagerFactory trustManagerFactory = getTrustManagerFactory(trustedCertificate);
        final SSLContext sslContext = getSSLContext(trustManagerFactory);
        X509TrustManager trustManager = getX509TrustManager(trustManagerFactory);
        okhttpBuilder.sslSocketFactory(sslContext.getSocketFactory(), trustManager);
        return okhttpBuilder;
    }

    private KeyStore getTrustedCertificate() {
        KeyStore trusted = null;
        InputStream in = null;
        try {
            trusted = KeyStore.getInstance(CERTIFICATE_TYPE);
            in = context.getResources().openRawResource(certificate);
            trusted.load(in, certificatePassword.toCharArray());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return trusted;
    }

    private TrustManagerFactory getTrustManagerFactory(KeyStore trustedCertificate) {
        TrustManagerFactory trustManagerFactory = null;
        try {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustedCertificate);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return trustManagerFactory;
    }

    private SSLContext getSSLContext(TrustManagerFactory trustManagerFactory) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance(DEFAULT_TLS_VERSION);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    private X509TrustManager getX509TrustManager(TrustManagerFactory trustManagerFactory) {
        final TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        if (trustManagers == null
                || trustManagers.length != 1
                || !(trustManagers[0] instanceof X509TrustManager)) {

            final IllegalStateException e = new IllegalStateException("Wrong trust manager: " + Arrays.toString(trustManagers));
            Log.e("ThrowableReporter", e.toString());
            throw e;
        }

        return (X509TrustManager) trustManagers[0];
    }
}
