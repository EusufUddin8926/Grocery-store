package com.example.grocerygo.model;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.grocerygo.Utils;

import java.util.ArrayList;

public class GroceryItem implements Parcelable {

    private  int id;
    private String name;
    private String description;
    private String imageUrl;
    private String Category;
    private  int availableAmount;
    private double price;
    private int popularitypoint;
    private int userpoint;
    private ArrayList<Review> reviews;

    public GroceryItem( String name, String description, String imageUrl,
                       String category, int availableAmount, double price
                       ) {
        this.id = Utils.getID();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        Category = category;
        this.availableAmount = availableAmount;
        this.price = price;
        this.popularitypoint = 0 ;
        this.userpoint = 0;
        this.reviews = new ArrayList<>();
    }

    protected GroceryItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        Category = in.readString();
        availableAmount = in.readInt();
        price = in.readDouble();
        popularitypoint = in.readInt();
        userpoint = in.readInt();
        reviews = in.createTypedArrayList(Review.CREATOR);
    }


    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPopularitypoint() {
        return popularitypoint;
    }

    public void setPopularitypoint(int popularitypoint) {
        this.popularitypoint = popularitypoint;
    }

    public int getUserpoint() {
        return userpoint;
    }

    public void setUserpoint(int userpoint) {
        this.userpoint = userpoint;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", Category='" + Category + '\'' +
                ", availableAmount=" + availableAmount +
                ", price=" + price +
                ", popularitypoint=" + popularitypoint +
                ", userpoint=" + userpoint +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(Category);
        dest.writeInt(availableAmount);
        dest.writeDouble(price);
        dest.writeInt(popularitypoint);
        dest.writeInt(userpoint);
        dest.writeTypedList(reviews);
    }
}
