package com.iigo.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartBezerActivity(View view) {
        startActivity(new Intent(this, BezerActivity.class));
    }

    public void onStartBubbleActivity(View view) {
        startActivity(new Intent(this, QQBubbleActivity.class));
    }

    public void onStartChargingActivity(View view) {
        startActivity(new Intent(this, ChargingActivity.class));
    }
}
