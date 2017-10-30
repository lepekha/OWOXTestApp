package com.lepekha.owoxtestapp.model.api;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Constants;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
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


    OkHttpClient provideHttpClient(){
                 return new OkHttpClient
                         .Builder()
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
                             })
                         .build();
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
