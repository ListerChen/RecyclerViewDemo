
package com.lister.recyclerviewdemo.decoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lister.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseCityActivity extends AppCompatActivity {

    private RecyclerView mCityRecyclerView;
    private SideBar mSideBar;
    private TextView mSectionTextView;
    private int mTextViewHeight;
    private List<CityInfo> mCityInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        initView();

        prepareCityInfo();
        final CityAdapter cityAdapter = new CityAdapter(this, mCityInfoList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCityRecyclerView.setAdapter(cityAdapter);
        mCityRecyclerView.setLayoutManager(linearLayoutManager);
        mCityRecyclerView.addItemDecoration(new CityItemDecoration(mCityInfoList));

        mSideBar.setOnTouchLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = cityAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    linearLayoutManager.scrollToPositionWithOffset(position, 0);
                }
            }
        });
        mSideBar.setOnTextPositionChangedListener(new SideBar.OnTextPositionChangedListener() {
            @Override
            public void onTextPositionChanged(String c, int y) {
                mSectionTextView.setVisibility(View.VISIBLE);
                int sideBarY = mSideBar.getTop();
                int topMargin = sideBarY + y - mSectionTextView.getHeight() / 2;
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mSectionTextView.getLayoutParams();
                lp.topMargin = topMargin;
                mSectionTextView.setLayoutParams(lp);
                mSectionTextView.setText(c);
            }

            @Override
            public void onActionUp() {
                mSectionTextView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initView() {
        mCityRecyclerView = findViewById(R.id.city_list);
        mSideBar = findViewById(R.id.city_side_bar);
        mSectionTextView = findViewById(R.id.city_section_text);
    }

    private void prepareCityInfo() {
        mCityInfoList = new ArrayList<>();
        String[] cityArray = getResources().getStringArray(R.array.city);
        // 侧边栏的索引字母
        ArrayList<String> indexList = new ArrayList<>();
        String curGroup = "0";
        for (int i = 0; i < cityArray.length; i += 3) {
            CityInfo cityInfo = new CityInfo(cityArray[i], cityArray[i + 1], cityArray[i + 2],
                    false, false);
            if (!cityInfo.getGroup().equals(curGroup)) {
                // 如果当前城市的 group 信息与保存的不一致, 那么就是该 group 的第一个
                cityInfo.setIsFirstInGroup(true);
                // 同时将该 group 信息添加到索引中
                indexList.add(cityInfo.getGroup());
                // 它的上一个城市就是上一个 group 的最后一个
                if (i > 0) {
                    mCityInfoList.get(mCityInfoList.size() - 1).setIsLastInGroup(true);
                }
                curGroup = cityInfo.getGroup();
            }
            mCityInfoList.add(cityInfo);
        }
        mSideBar.setIndexText(indexList);
    }
}
