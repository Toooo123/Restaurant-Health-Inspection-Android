package com.example.restauranthealthinspector.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.restauranthealthinspector.R;
import com.example.restauranthealthinspector.model.Restaurant;
import com.example.restauranthealthinspector.model.RestaurantsManager;

import java.io.IOException;

public class RestaurantActivity extends AppCompatActivity {
    private RestaurantsManager myRestaurants;
    private Restaurant restaurant;
    private int indexRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        try {
            myRestaurants = RestaurantsManager.getInstance(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadRestaurant();
    }

    private void loadRestaurant() {
        Intent intent = getIntent();
        indexRestaurant = intent.getIntExtra("indexRestaurant", 0);

        restaurant = myRestaurants.get(indexRestaurant);

        TextView restName = (TextView)findViewById(R.id.rest_txtName);
        String restaurantName = restaurant.getRestaurantName();
        restName.setText(restaurantName);

        TextView restAddress = (TextView)findViewById(R.id.rest_txtAddress);
        String restaurantAddress = restaurant.getAddress().getStreetAddress() +
                        ", " + restaurant.getAddress().getCity();
        restAddress.setText(restaurantAddress);

        TextView restLatitude = (TextView)findViewById(R.id.rest_txtLatitude);
        double restaurantLatitude = restaurant.getAddress().getLatitude();
        restLatitude.setText(Double.toString(restaurantLatitude));


        TextView restLongitude = (TextView)findViewById(R.id.rest_txtLongitude);
        double restaurantLongitude = restaurant.getAddress().getLongitude();
        restLongitude.setText(Double.toString(restaurantLongitude));
    }

    private void setUpInspectionClick() {
        ListView list = findViewById(R.id.restlist_listRestaurants);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                Intent intent = new Intent(RestaurantActivity.this, InspectionActivity.class);
                intent.putExtra("indexRestaurant", indexRestaurant);
                intent.putExtra("indexInspection", position);
                startActivity(intent);
            }
        });
    }
}