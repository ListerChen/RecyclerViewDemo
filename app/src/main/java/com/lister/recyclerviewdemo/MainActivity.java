
package com.lister.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lister.recyclerviewdemo.animator.AnimatorActivity;
import com.lister.recyclerviewdemo.decoration.ChooseCityActivity;
import com.lister.recyclerviewdemo.inflate.TestInflateActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_inflate).setOnClickListener(this);
        findViewById(R.id.btn_city).setOnClickListener(this);
        findViewById(R.id.btn_animator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_inflate:
                startActivity(new Intent(this, TestInflateActivity.class));
                break;
            case R.id.btn_city:
                startActivity(new Intent(this, ChooseCityActivity.class));
                break;
            case R.id.btn_animator:
                startActivity(new Intent(this, AnimatorActivity.class));
                break;
        }
    }
}
