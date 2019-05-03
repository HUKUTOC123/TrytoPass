package com.example.nikita99.trytopass;

import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nikita99.trytopass.roomDataBase.AppDataBase;
import com.example.nikita99.trytopass.roomDataBase.Distance;
import com.example.nikita99.trytopass.roomDataBase.DistanceDao;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    private List<String> listData = new ArrayList<>();
    private ListView listView;
    double rast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppDataBase db = App.getInstance().getDatabase();
        DistanceDao employeeDao = db.distanceDao();

        for(Distance distance: employeeDao.getAll()){
            LatLng orig= new LatLng(distance.getStartLat(),distance.getStartLng());
            LatLng destpoint= new LatLng(distance.getEndLat(), distance.getEndLng());
            rast = destpoint.distanceTo(orig);
            rast=Math.round(rast * 100.0) / 100.0;
            String coordinates =
                    "Начало:\n" +
                            getAddress(distance.getStartLat(),distance.getStartLng())
                            + ", "
                            + "\nКонец:\n"
                            + getAddress(distance.getEndLat(), distance.getEndLng())
                            + ", "
                            + "\nРасстояние:\n"
                            +rast+"  метров";
            listData.add(coordinates);
        }
        listView = (ListView) findViewById(R.id.recycle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        if(listData != null) {
            listView.setAdapter(adapter);
        }
    }

    private String getAddress(double longitude, double latitude) {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = addresses.get(0).getAddressLine(0);
        return addresses.get(0).getAddressLine(0);
    }
}