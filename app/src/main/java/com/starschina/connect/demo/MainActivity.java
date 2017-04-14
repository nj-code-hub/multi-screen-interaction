package com.starschina.connect.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.starschina.connect.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @BindView(R.id.moblie)
    Button moblie;
    @BindView(R.id.tv)
    Button tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.moblie)
    public void toMobilePage() {
        Intent i = new Intent(this, MobileActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.tv)
    public void toTvPege() {
        Intent i = new Intent(this, TvActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
