
package com.lister.recyclerviewdemo.inflate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lister.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class TestInflateActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_inflate);

        mRecyclerView = findViewById(R.id.test_inflate_rv);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("data: " + i);
        }
        mRecyclerView.setAdapter(new TestInflateAdapter(this, data));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
