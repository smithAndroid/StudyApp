package com.smith.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.app_text_view);
        new Thread(){
            @Override
            public void run() {
                super.run();
                justTest();
//                textView.setText("Hello HUAWEI"+Thread.currentThread().getName());
            }
        }.start();

    }

    private void justTest() {
//        Hello h = new Hello();
//        h.name = "中国";
//        h.age = "五千年";
//        Hello o;
//        o = h;
//        Log.i("smith","-------"+h.name+"----"+h.age);
//        h = null;
//        Log.i("smith","-------"+o.name+"----"+o.age);
        Log.i("smith","---textView.getWidth()--"+textView.getWidth()+
                "-----textView.getHeight()-----"+textView.getHeight());

        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("smith","-Runnable----textView.getWidth()--"+textView.getWidth()+
                        "-----textView.getHeight()-----"+textView.getHeight());
                textView.setText("Hello OPPO"+Thread.currentThread().getName());
            }
        });

    }

    private class Hello {
        String name;
        String age;
    }
}
