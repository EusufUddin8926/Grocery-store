package com.example.grocerygo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.grocerygo.model.GroceryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String TAG = "Utils";

    public static final String Database_Name = "grocery_database";
    private  static int ID = 0;

    public Utils() {
    }

    public static int getID(){
        ID++;
        return ID;
    }

    public void initdatabase(Context context){
        Log.d(TAG, "initdatabase: started");

        SharedPreferences sharedPreferences = context.getSharedPreferences(Database_Name,context.MODE_PRIVATE);


        Gson gson = new Gson();

        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
        ArrayList<GroceryItem> possibleItems = gson.fromJson(sharedPreferences.getString("allItems",""),type);

        if(null== possibleItems){

            initallitem(context);

        }

    }

    public ArrayList<GroceryItem> getAllItems(Context context){
        Log.d(TAG, "getAllItems: started");
        Gson gson = new Gson();
        SharedPreferences sharedPreferences =context.getSharedPreferences(Database_Name,context.MODE_PRIVATE);
        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);
        return allItems;
    }
    public void initallitem(Context context){
        Log.d(TAG, "initallitem: started");


        SharedPreferences sharedPreferences = context.getSharedPreferences(Database_Name,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();


        ArrayList<GroceryItem>  allItems = new ArrayList<>();

        GroceryItem iceCream = new GroceryItem("Ice Cream","Produce Of Fresh Milk",
                "https://upload.wikimedia.org/wikipedia/commons/3/31/Ice_Cream_dessert_02.jpg","Ice_Cream",
                20,20);
        iceCream.setPopularitypoint(10);

        allItems.add(new GroceryItem("Banana","Best banana Possible",
                "https://upload.wikimedia.org/wikipedia/commons/1/10/Banana_isolated_on_white.jpg",
                "Fruit",15,5.00));

        allItems.add(new GroceryItem("Apple","Best apple Possible",
                "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg",
                "Food",15,5.00));

        allItems.add(new GroceryItem("Mango","Best guava Possible",
                "https://upload.wikimedia.org/wikipedia/commons/0/02/Guava_ID.jpg",
                "guava",15,5.00));

       iceCream.setUserpoint(10);
        allItems.add(iceCream);



        String finalstring = gson.toJson(allItems);
        editor.putString("allItems",finalstring);
        editor.commit();
    }

}
