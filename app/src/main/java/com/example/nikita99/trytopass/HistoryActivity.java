package com.example.nikita99.trytopass;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nikita99.trytopass.roomDataBase.AppDataBase;
import com.example.nikita99.trytopass.roomDataBase.Distance;
import com.example.nikita99.trytopass.roomDataBase.DistanceDao;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<Distance> allData;
    private List<String> listData;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppDataBase db = App.getInstance().getDatabase();
        DistanceDao employeeDao = db.distanceDao();
        allData = employeeDao.getAll();

        listData = new ArrayList<String>();

        for(Distance distance: allData){
            String coordinates =
                    "Координаты старта:\n" +
                    Double.toString(distance.getStartLat())
                    + ", "
                    + Double.toString(distance.getStartLng())
                    + ", "
                    + "\nКоординаты конца:\n"
                    + Double.toString(distance.getEndLat())
                    +", "
                    +Double.toString(distance.getEndLng());
            listData.add(coordinates);
        }

        listView = (ListView) findViewById(R.id.recycle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        listView.setAdapter(adapter);
    }
}
