package com.ln.layouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        LinearLayout llayout = findViewById(R.id.main_linearLay);
        LinearLayout llaySub = new LinearLayout(this);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llaySub.setOrientation(LinearLayout.HORIZONTAL);

        ImageView i = new ImageView(this);
        i.setImageDrawable(getResources().getDrawable(R.drawable.oo));

        TextView t = new TextView(this);
        t.setText("这是新加入的大文字");

        llaySub.addView(i);
        llaySub.addView(t);
        llayout.addView(llaySub);



    }
}
