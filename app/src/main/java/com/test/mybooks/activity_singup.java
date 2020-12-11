package com.test.mybooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_singup extends AppCompatActivity {

    EditText txt_first_name,txt_last_name,txt_email,txt_ph,txt_pass,txt_cpass;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        txt_first_name=findViewById(R.id.txt_f_name);
        txt_last_name=findViewById(R.id.txt_l_name);
        txt_email=findViewById(R.id.txt_email);
        txt_ph=findViewById(R.id.txt_ph);
        txt_pass=findViewById(R.id.txt_pass);
        txt_cpass=findViewById(R.id.txt_cpass);
        btn_signup=findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_first_name.getText().toString().equals("")) {
                    Toast.makeText(activity_singup.this, "Please type a firname", Toast.LENGTH_SHORT).show();

                }else if(txt_last_name.getText().toString().equals("")) {
                    Toast.makeText(activity_singup.this, "Please type last name", Toast.LENGTH_SHORT).show();

                }else if(txt_email.getText().toString().equals("")){
                    Toast.makeText(activity_singup.this, "Please type email", Toast.LENGTH_SHORT).show();

                }
                else if(txt_ph.getText().toString().equals("")){
                    Toast.makeText(activity_singup.this, "Please type phone number", Toast.LENGTH_SHORT).show();

                }
                else if(!txt_pass.getText().toString().equals(txt_cpass.getText().toString())){
                    Toast.makeText(activity_singup.this, "Password mismatch", Toast.LENGTH_SHORT).show();

                }else {
                        perform_signup(txt_first_name.getText().toString(),txt_last_name.getText().toString(),txt_ph.getText().toString(),txt_email.getText().toString(),txt_pass.getText().toString());
                     }
            }
        });
    }


    public void perform_signup(String fname,String lname ,String ph,String email,String password) {

        ApiInterface mApiService = Controller.getInterfaceService();

        Call<String> responseBodyCall = mApiService.Signup(fname,lname,ph,email,password);

        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){
                    startActivity(new Intent(activity_singup.this,activity_books.class));
                    Toast.makeText(activity_singup.this, "Successfully Signup", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(activity_singup.this, "Faild !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity_singup.this, t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}