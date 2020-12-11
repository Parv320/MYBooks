package com.test.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_book_details extends AppCompatActivity {

    TextView txt_book_name,txt_book_author,txt_book_price,txt_book_description;
    ImageView img;
    Button btn_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        img=findViewById(R.id.book_img);
        txt_book_name=findViewById(R.id.txt_book_name);
        txt_book_author=findViewById(R.id.txt_book_author);
        txt_book_price=findViewById(R.id.txt_book_price);
        txt_book_description=findViewById(R.id.txt_book_description);
        btn_cart=findViewById(R.id.btn_add_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_book_details.this,activity_cart.class));
            }
        });
        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            txt_book_name.setText((String) b.get("name"));
            txt_book_author.setText((String) b.get("author"));
            txt_book_price.setText((String) b.get("price"));
            txt_book_description.setText((String) b.get("description"));
            Picasso.get().load((String) b.get("image")).into(img);
        }
    }
}