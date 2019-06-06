package com.ln.layouttest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ln.entity.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpActivity extends AppCompatActivity {

    private TextView textView;

    private EditText nUname;

    private EditText nPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        nUname = findViewById(R.id.uname_ok);
        nPwd = findViewById(R.id.pwd_ok);
        textView = findViewById(R.id.response_data_okhttp);

        findViewById(R.id.send_request_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendByOKHttp(); // 测试连接http，连接真实后台，传递参数，及返回结果

                String uname = nUname.getText().toString(); //得到账号文字
                String pwd = nPwd.getText().toString(); // 得到密码文字
                emulateLogin(uname, pwd); // 模拟登录
            }
        });


    }


    /**
     * 发送请求（使用 OKHttp）
     */
    private void sendByOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaType JSON = MediaType.get("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();

                String uname = nUname.getText().toString();
                String pwd = nPwd.getText().toString();

                emulateLogin(uname, pwd);

                User user = new User(uname, pwd);
                Gson g = new Gson();
                String userString = g.toJson(user);

                FormBody formBody = new FormBody.Builder()
                        .add("user", userString)
                        .build();


                RequestBody body = RequestBody.create(JSON, userString);
                Request request = new Request.Builder().url("http://2a508618c0.qicp.vip/hello").post(formBody).build();
                try {
                    Call call = client.newCall(request);//发送请求
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("error", "连接失败！");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            Gson g1 = new Gson();
                            User userResult = g1.fromJson(result, User.class);
                            Log.i("result", "用户名: " + userResult.getUname() + "，密码：" + userResult.getPwd());
                            show(result);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 展示
     * @param result
     */
    private void show(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(result);
                textView.setTextColor(Color.RED);
            }
        });
    }


    /**
     * 模拟登录，为跳转页面用
     * @param uname
     * @param pwd
     */
    public void emulateLogin(String uname, String pwd){
        if(uname.equals("666") && pwd.equals("666")){
            Intent intent = new Intent(OkHttpActivity.this, ScrollViewActivity.class);
            startActivity(intent);
            Toast.makeText(OkHttpActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(OkHttpActivity.this, "账号或密码不匹配，登录不成功！", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
