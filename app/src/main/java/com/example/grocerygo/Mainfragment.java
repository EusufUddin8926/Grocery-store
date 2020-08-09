package com.example.grocerygo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerygo.model.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Mainfragment extends Fragment {
    private static final String TAG = "Mainfragment";

    private RecyclerView newItemRecView, popularItemRecView, suggestedItemRecView;

    private BottomNavigationView bottomNavigationView;

    private GroceryItemAdapter newItemAdapter,popularItemAdapter,suggestedItemAdapter;

    private Utils utils;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container,false);
        initView(view);

        initBottomNavigation();

        utils = new Utils();
        utils.initdatabase(getActivity() );

        initRecView();

        return view;
    }

    private void initRecView() {
        Log.d(TAG, "initRecView: started");

        newItemAdapter = new GroceryItemAdapter(getActivity());
        popularItemAdapter = new GroceryItemAdapter(getActivity());
        suggestedItemAdapter = new GroceryItemAdapter(getActivity());


        newItemRecView.setAdapter(newItemAdapter);
        popularItemRecView.setAdapter(popularItemAdapter);
        suggestedItemRecView.setAdapter(suggestedItemAdapter);

        newItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        suggestedItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        ArrayList<GroceryItem> allItems = utils.getAllItems(getActivity());

       Comparator<GroceryItem> newItemComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getId() - o2.getId();
            }
        };

        Comparator<GroceryItem> reverseNewItemComparator = Collections.reverseOrder(newItemComparator);
        Collections.sort(allItems,reverseNewItemComparator);


        if (null != allItems) {
            newItemAdapter.setItems(allItems);
        }

        ArrayList<GroceryItem>  popularItem = utils.getAllItems(getActivity());

        Comparator<GroceryItem> popularityComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return compareByPopularity(o1, o2);
            }
        };

        Comparator<GroceryItem> reversePopularityComparator = Collections.reverseOrder(popularityComparator);
        Collections.sort(popularItem, reversePopularityComparator);

        popularItemAdapter.setItems(popularItem);



        ArrayList<GroceryItem> suggestedItem = utils.getAllItems(getActivity());

        Comparator<GroceryItem> suggestedItemcomparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getUserpoint()- o2.getUserpoint();
            }
        };


        Comparator<GroceryItem> reverseSuggestedItemComparator = Collections.reverseOrder(suggestedItemcomparator);
        Collections.sort(suggestedItem, reverseSuggestedItemComparator);

        suggestedItemAdapter.setItems(suggestedItem);


    }

    private int compareByPopularity (GroceryItem item1, GroceryItem item2) {
        Log.d(TAG, "compareByPopularity: Started");


        if (item1.getPopularitypoint() > item2.getPopularitypoint()) {

            return 1;
        } else if (item1.getPopularitypoint() < item2.getPopularitypoint()) {
            return -1;
        } else {
            return 0;
        }
    }

    public void initBottomNavigation(){
        Log.d(TAG, "initBottomNavigation: started");

        bottomNavigationView.setSelectedItemId(R.id.Home);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.Search:

                        break;
                    case R.id.Home:

                        break;
                    case R.id.Cart:

                        break;

                    default:
                        break;


                }
            }
        });
    }

    public void initView(View view){
        Log.d(TAG, "initView: started");
        bottomNavigationView = view.findViewById(R.id.bottomNavigation);
        newItemRecView = view.findViewById(R.id.newItemrecView);
        popularItemRecView = view.findViewById(R.id.popularItem);
        suggestedItemRecView = view.findViewById(R.id.SuggestedItem);

    }
}

