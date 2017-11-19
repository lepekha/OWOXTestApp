package com.lepekha.owoxtestapp.model.api;

import android.os.Build;
import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruslan on 12.08.2016.
 */
public class APIhelper {

    public APIhelper() {
        App.getComponent().inject(this);
    }

    /**Добавляем новый tls 1.2 на старые устройства*/
    public OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }

    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
            .tlsVersions(TlsVersion.SSL_3_0)
            .build();

    OkHttpClient provideHttpClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                         .connectTimeout(30, TimeUnit.SECONDS)
                         .writeTimeout(30, TimeUnit.SECONDS)
                         .readTimeout(30, TimeUnit.SECONDS)
                         .addInterceptor(new Interceptor() {
                                 @Override
                                 public Response intercept(Chain chain) throws IOException {
                                         Request request = chain.request();
                                                 request = request.newBuilder().header("Accept", "application/json").build();
                                         return chain.proceed(request);
                                     }
                             });
        return enableTls12OnPreLollipop(client).build();
             }




    public UnsplashApi getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideHttpClient())
                .build();

        return retrofit.create(UnsplashApi.class);
    }
}
