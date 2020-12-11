package com.test.mybooks;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<String> Login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<String> Signup(@Field("fname") String fname, @Field("lname") String lname,@Field("ph") String ph,@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("add_book")
    Call<String> Add_Book(@Field("book_name") String book_name, @Field("author") String author,@Field("price") String price,@Field("description") String description, @Field("image") String image);


    @GET("list")
    Call <JsonObject> FetchBooks();


}
