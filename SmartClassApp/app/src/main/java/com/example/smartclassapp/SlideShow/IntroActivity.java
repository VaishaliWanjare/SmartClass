package com.example.smartclassapp.SlideShow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.smartclassapp.MainActivity;
import com.example.smartclassapp.R;
import com.example.smartclassapp.SignUp;


import java.util.ArrayList;
import java.util.List;


public class IntroActivity extends AppCompatActivity {

    private ViewPager screenpager;
    IntroViewPageAdapter introViewPageAdapter;
    Button next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slide_screen_intro);


        next_button = findViewById(R.id.next);

        // fill list screen

        List<ScreenItem> mlist = new ArrayList<>();
        mlist.add(new ScreenItem("Smart Class","A new and sorted way of learning...",R.drawable.ss1));
        mlist.add(new ScreenItem("Start with L'Earn","Let get started register and enjoy learning with our app...Click on Next to get started",R.drawable.ss2));

        //setupViewpager
        screenpager=findViewById(R.id.screen_Viewpager);
        introViewPageAdapter = new IntroViewPageAdapter(this,mlist);
        screenpager.setAdapter(introViewPageAdapter);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}
