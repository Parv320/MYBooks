package com.test.mybooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_add_books extends AppCompatActivity {

    EditText txt_book_name,txt_author,txt_price,txt_description,txt_image_url;
    Button btn_add;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        firebaseFirestore= FirebaseFirestore.getInstance();
        txt_book_name=findViewById(R.id.txt_book_name);
        txt_author=findViewById(R.id.txt_book_author);
        txt_price=findViewById(R.id.txt_book_price);
        txt_description=findViewById(R.id.txt_book_description);
        txt_image_url=findViewById(R.id.txt_book_image);
        btn_add=findViewById(R.id.btn_add_book);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                perform_add_book(txt_book_name.getText().toString(),txt_author.getText().toString(),txt_price.getText().toString(),txt_description.getText().toString(),txt_image_url.getText().toString());
            }
        });

    }

    public void perform_add_book(String bookname,String author ,String price,String description,String image) {

        ApiInterface mApiService = Controller.getInterfaceService();

        Call<String> responseBodyCall = mApiService.Add_Book(bookname,author,price,description,image);

        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){
                    Toast.makeText(activity_add_books.this, "Successfully Added", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(activity_add_books.this, "Faild !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity_add_books.this, t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(activity_add_books.this,activity_books.class));
    }
}