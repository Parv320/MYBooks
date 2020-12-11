package com.test.mybooks;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class books_viewholder extends RecyclerView.Adapter<books_viewholder.products>{

    Context context;
    ArrayList <Books> books;

    public books_viewholder(Context c , ArrayList<Books> _books)
    {
        context = c;
        books = _books;
    }


    @NonNull
    @Override
    public products onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new products(LayoutInflater.from(context).inflate(R.layout.layout_list,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final products holder, final int position) {

        holder.Name.setText( books.get(position).getName());
        holder.Author.setText("Author : "+books.get(position).getAuthor());
        holder.Price.setText("Price : "+books.get(position).getPrice()+"$");
        Picasso.get().load(books.get(position).getImage()).into(holder.Image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, activity_book_details.class);
                i.putExtra("name", books.get(position).getName());
                i.putExtra("author", books.get(position).getAuthor());
                i.putExtra("price", books.get(position).getPrice()+"$");
                i.putExtra("description", books.get(position).getDescription());
                i.putExtra("image", books.get(position).getImage());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return books.size();
    }

    class products extends RecyclerView.ViewHolder{
        TextView Name,Author,Price;
        ImageView Image;
        CardView cardView;

        public products(View itemView){
            super(itemView);

            Name  = (TextView) itemView.findViewById(R.id.txt_book);
            Author  = (TextView) itemView.findViewById(R.id.txt_author);
            Price  = (TextView) itemView.findViewById(R.id.txt_price);
            Image  = (ImageView) itemView.findViewById(R.id.imageItem);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
        }
    }








}
