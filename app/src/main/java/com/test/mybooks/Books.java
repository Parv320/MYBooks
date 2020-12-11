package com.test.mybooks;

import org.json.JSONObject;

public class Books {
    private String Name,Author,Price,Description,Image;


    public Books(JSONObject object)
    {
        try
        {
            this.Name = object.getString("book_name");
            this.Author = object.getString("author");
            this.Price = object.getString("price");
            this.Description = object.getString("description");
            this.Image = object.getString("image");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Books(String name, String author, String price, String description, String image) {
        Name = name;
        Author = author;
        Price = price;
        Description = description;
        Image = image;
    }
    public  Books(){

    }
}
