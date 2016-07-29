package com.malenalbc.superherotest.data.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.data.model.ResultWrapper;
import com.malenalbc.superherotest.security.DigestUtils;

import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

public class Client {
    private static final String BASE_URL = "http://gateway.marvel.com";
    private static final int CHARACTER_ID = 1009629;
    private static final String API_KEY = "95bcc54e672fb808b02a7dd427dd6ad1";
    private static final String PRIVATE_API_KEY = "3d98b84062b55ea4d8c255c21b212e3ecabe79f3";

    public static <S> S createService (Class<S> serviceClass) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        // Define the interceptor, add authentication headers
        Interceptor headerInterceptor = chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            final String timestamp = String.valueOf(new Date().getTime());
            final String hash = DigestUtils.md5Hex((timestamp + PRIVATE_API_KEY + API_KEY));

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("ts", timestamp)
                    .addQueryParameter("hash", hash)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(headerInterceptor);
        OkHttpClient client = builder.build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(
                Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(serviceClass);
    }

    /**
     * In one interface for simplicity purposes. Every use case should be separated in each own
     * class.
     */
    public interface ClientService {
        @GET("/v1/public/characters/" + CHARACTER_ID)
        Observable<ResultWrapper<Character>> getCharacter ();

        @GET("/v1/public/characters/" + CHARACTER_ID + "/comics")
        Observable<ResultWrapper<Comic>> getComics (@Query("dateRange") String dateRange);
    }

}
