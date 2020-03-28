
package com.lister.recyclerviewdemo.turntable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lister.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class TurntableActivity extends AppCompatActivity {

    private RecyclerView mTurntableRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turntable);

        int[] pics = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
                R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
                R.drawable.pic10, R.drawable.pic11};
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pics.length; j++) {
                data.add(pics[j]);
            }
        }
        mTurntableRecyclerView = findViewById(R.id.turntable_rv);
        TurntableAdapter adapter = new TurntableAdapter(this, data);
        TurntableLayoutManager turntableLayoutManager = new
                TurntableLayoutManager(2000, 12);
        mTurntableRecyclerView.setAdapter(adapter);
        mTurntableRecyclerView.setLayoutManager(turntableLayoutManager);
        mTurntableRecyclerView.setItemAnimator(null);
    }
}
