package com.iigo.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/7/2 0002 16:41
 */
public class QQBubbleActivity extends AppCompatActivity {
    private FunnyBubbleView funnyBubbleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bubble);
        funnyBubbleView = findViewById(R.id.fbv_bubble);
    }

    public void onReset(View view) {
        funnyBubbleView.reset();
    }
}
