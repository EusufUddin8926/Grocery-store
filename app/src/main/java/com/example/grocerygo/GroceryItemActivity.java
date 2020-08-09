package com.example.grocerygo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grocerygo.model.GroceryItem;

public class GroceryItemActivity extends AppCompatActivity {

    private static final String TAG = "GroceryItemActivity";

    private TextView txtname,txtprice,txtdescription,txtAvailability;
    private ImageView itemimage;
    private Button AddtoCart;

    private ImageView firstFilledStar,secoundFilledStar,thirdFilledStar,firstEmptyStar,secoundEmptyStar,thirdEmptyStar;

    private RecyclerView reviewRecView;
    private RelativeLayout addReviewRelLayout;

    private GroceryItem incomingItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);

        Intent intent = getIntent();
        try{

            incomingItem = intent.getParcelableExtra("item");
            setViewValues();

        }catch (NullPointerException e){

            e.printStackTrace();
        }

        initViews();
    }

    /**
     * responsible for setting the initial value for views
     */

    public  void  setViewValues(){
        Log.d(TAG, "setViewValues: Started");

        txtname.setText(incomingItem.getName());
        txtprice.setText(String.valueOf(incomingItem.getPrice() + "$"));
        txtAvailability.setText(incomingItem.getAvailableAmount() + " number (s) available");
        txtdescription.setText(incomingItem.getDescription());

        Glide.with(this)
                .asBitmap()
                .load(incomingItem.getImageUrl())
                .into(itemimage);

        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO: Add to Cart
            }
        });

        //TODO: handle the start stitution

    }

    private void initViews(){
        Log.d(TAG, "initViews: started");

        txtname = findViewById(R.id.txtname);
        txtprice = findViewById(R.id.txtprice);
        txtAvailability = findViewById(R.id.txtAvailability);

        itemimage = findViewById(R.id.itemImage);

        AddtoCart  = findViewById(R.id.buttonAddtoCart);

        firstFilledStar = findViewById(R.id.firstFilledStar);
        secoundFilledStar = findViewById(R.id.secoundFilledStar);
        thirdFilledStar = findViewById(R.id.ThirdFilledStar);
        firstEmptyStar = findViewById(R.id.firstEmptyStar);
        secoundEmptyStar = findViewById(R.id.secoundEmptyStar);
         thirdEmptyStar = findViewById(R.id.thirdEmptyStar);

         reviewRecView = findViewById(R.id.reviewRecView);

         addReviewRelLayout = findViewById(R.id.AddreviewRellayout);


    }
}
