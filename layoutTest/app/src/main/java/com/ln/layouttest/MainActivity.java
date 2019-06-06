package com.ln.layouttest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout = null;
    private Button button;
    EditText uname;
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams aparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(aparams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        // 生成账号输入框
        uname = new EditText(getApplicationContext());
        LinearLayout.LayoutParams bparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        uname.setLayoutParams(bparams);
        uname.setId(R.id.uname);
        SpannableString hintone = new SpannableString("请输入账号");
        uname.setHint(hintone);
        uname.setHintTextColor(Color.RED);
        uname.setTextColor(Color.BLACK);


        Drawable drone = getResources().getDrawable(R.color.colorPrimary);
        uname.setCompoundDrawablesWithIntrinsicBounds(drone, null, null, null);
        uname.setCompoundDrawablePadding(5);

        // 生成密码输入框
        pwd = new EditText(getApplicationContext());
        pwd.setLayoutParams(bparams);
        pwd.setId(R.id.pwd);
        SpannableString hinttwo = new SpannableString("请输入密码");
        pwd.setHint(hinttwo);
        pwd.setHintTextColor(Color.RED);
        pwd.setTextColor(Color.BLACK);

        Drawable drone1 = getResources().getDrawable(R.color.colorAccent);
        pwd.setCompoundDrawablesWithIntrinsicBounds(drone1, null, null, null);
        pwd.setCompoundDrawablePadding(5);

        linearLayout.addView(uname);
        linearLayout.addView(pwd);

        button = new Button(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params2);
        button.setText("登录");
        button.setBackgroundColor(Color.YELLOW);
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
            }
        });
    }


}
