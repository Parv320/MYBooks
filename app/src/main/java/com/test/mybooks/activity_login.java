package com.test.mybooks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_login extends AppCompatActivity {
    EditText txt_email,txt_pass;
    Button btn_sign_in,btn_sign_up;
    FirebaseFirestore firebaseFirestore;
    public  boolean auth=false;
    private static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_email=findViewById(R.id.txt_email);
        txt_pass=findViewById(R.id.txt_pass);
        btn_sign_in=findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_email.getText().toString().equals("")){
                    Toast.makeText(activity_login.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }else if( txt_pass.getText().toString().equals("")){
                    Toast.makeText(activity_login.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {

                        perform_login(txt_email.getText().toString(),txt_pass.getText().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(activity_login.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_sign_up=findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_login.this,activity_singup.class);
                startActivity(intent);
            }
        });

    }




    public void perform_login(String email,String password) {

        ApiInterface mApiService = Controller.getInterfaceService();

        Call<String> responseBodyCall = mApiService.Login(email,password);

        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){
                    startActivity(new Intent(activity_login.this,activity_books.class));
                }
                else{
                    Toast.makeText(activity_login.this, "Faild ! check your username and password ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(activity_login.this, t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }





    public static void showLoader(Context con, String msg)
    {
        progressDialog = new ProgressDialog(con);
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
    public static void stopLoader()
    {
        if (progressDialog != null)
        {

            progressDialog.cancel();
            progressDialog = null;

        }
    }
}