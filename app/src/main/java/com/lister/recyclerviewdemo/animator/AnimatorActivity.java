
package com.lister.recyclerviewdemo.animator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lister.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditPosition;
    private Button mBtnAdd;
    private Button mBtnRemove;
    private Button mBtnChange;
    private RecyclerView mRecyclerView;
    private AnimatorAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        initViews();
        mBtnAdd.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
        mBtnChange.setOnClickListener(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("我是数据: " + i);
        }
        mAdapter = new AnimatorAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new MyItemAnimator());
    }

    private void initViews() {
        mEditPosition = findViewById(R.id.animator_position);
        mBtnAdd = findViewById(R.id.animator_button_add);
        mBtnRemove = findViewById(R.id.animator_button_remove);
        mBtnChange = findViewById(R.id.animator_button_change);
        mRecyclerView = findViewById(R.id.animator_recycler_view);
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(mEditPosition.getText().toString());
        switch (v.getId()) {
            case R.id.animator_button_add:
                mData.add(position, "新增数据");
                mAdapter.notifyItemInserted(position);
                break;
            case R.id.animator_button_remove:
                mData.remove(position);
                mAdapter.notifyItemRemoved(position);
                break;
            case R.id.animator_button_change:
                mData.set(position, "改变数据");
                mAdapter.notifyItemChanged(position);
                break;
        }
    }
}
