package com.iigo.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/28 0028 16:30
 */
public class BezerActivity extends AppCompatActivity {
    private BezerView bezerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bezer);
        bezerView = findViewById(R.id.bv_bezer);
    }

    public void onRefresh(View view){
        bezerView.refresh();
    }
}
