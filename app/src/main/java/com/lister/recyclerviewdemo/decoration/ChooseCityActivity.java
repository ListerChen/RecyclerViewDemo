
package com.lister.recyclerviewdemo.decoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lister.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseCityActivity extends AppCompatActivity {

    private RecyclerView mCityRecyclerView;
    private List<CityInfo> mCityInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        initView();

        prepareCityInfo();
        CityAdapter cityAdapter = new CityAdapter(this, mCityInfoList);
        mCityRecyclerView.setAdapter(cityAdapter);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new CityItemDecoration(mCityInfoList));
    }

    private void initView() {
        mCityRecyclerView = findViewById(R.id.city_list);
    }

    private void prepareCityInfo() {
        mCityInfoList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CityInfo cityInfo = new CityInfo("name: " + i, "group: " + (i / 7),
                    i % 7 == 0, i % 7 == 6);
            mCityInfoList.add(cityInfo);
        }
    }
}
