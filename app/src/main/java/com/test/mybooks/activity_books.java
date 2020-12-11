package com.test.mybooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_books extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Books> list;
    books_viewholder adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        list=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activity_books.this, 1));

        fetch_books();
    }


    public void fetch_books() {

        ApiInterface mApiService = Controller.getInterfaceService();

        Call<JsonObject> responseBodyCall = mApiService.FetchBooks();

        responseBodyCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try
                {

                    JSONObject object = new JSONObject(String.valueOf(response.body()));
                    JSONArray data  = object.getJSONArray("results");
                    for(int index = 0; index < data.length(); index++)
                    {
                        JSONObject obj = data.getJSONObject(index);
                        Books info = new Books(obj);

                        list.add(info);
                    }

                    if(list.size() > 0) {
                        adapter = new books_viewholder(activity_books.this, list);
                        recyclerView.setAdapter(adapter);

                    }
                    else {

                    }

                }
                catch (Exception e){
                    Toast.makeText(activity_books.this, e.toString(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                finish();
                startActivity(new Intent(activity_books.this,activity_add_books.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}