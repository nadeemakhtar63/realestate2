package com.example.realestate;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private static final String TAG = "IntroActivity";
    private static final int REQUEST_CODE = 1;
    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // when this activity is about to be launch we need to check if its openened before or not
        if (restorePrefData()) {
           /* Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
            startActivity(mainActivity);
            finish();*/

        }

        setContentView(R.layout.activity_intro);


        // hide the action bar

//        getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        btnGetStarted.setVisibility(View.GONE);
        // fill list screen

        final List<com.example.realestate.ScreenItem> mList = new ArrayList<>();
        mList.add(new com.example.realestate.ScreenItem("Find Space anywhere","By using this application youmay violate the terms and services of other applications. Remember to review these terms and use of other apps before selecting them and do not select incompatible applications.",getResources().getDrawable(R.drawable.intro1)));
        mList.add(new com.example.realestate.ScreenItem("Smart Search experience","This appliction is free and to continue working on it, we have to show advertisement. To do this, our ad providers will use the information that they have gathered about  you. Remenber, read the policies and you have to be legal aged to continue. Our ad providers and privacy policies may vary. WhatsDelete does not use your notification for commercial purposes but it only save and detect its content in your device.",getResources().getDrawable(R.drawable.intro2)));
        mList.add(new com.example.realestate.ScreenItem("Easy, Quick and safe","This appliction is free and to continue working on it, we have to show advertisement. To do this, our ad providers will use the information that they have gathered about  you. Remenber, read the policies and you have to be legal aged to continue. Our ad providers and privacy policies may vary. WhatsDelete does not use your notification for commercial purposes but it only save and detect its content in your device.",getResources().getDrawable(R.drawable.intro3)));
//        mList.add(new com.example.realestate.ScreenItem("anothe text","This appliction is free and to continue working on it, we have to show advertisement. To do this, our ad providers will use the information that they have gathered about  you. Remenber, read the policies and you have to be legal aged to continue. Our ad providers and privacy policies may vary. WhatsDelete does not use your notification for commercial purposes but it only save and detect its content in your device.",getResources().getDrawable(R.drawable.intro3)));

//        mList.add(new com.example.realestate.ScreenItem("Recycler","This is App Selection",R.drawable.img3));
        mList.add(new com.example.realestate.ScreenItem("Ready To go","By clicking accept, you accept our advertising policies and do not violate the terms of use of other applications.",getResources().getDrawable(R.drawable.intro3)));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                Log.i(TAG, "onClick: position"+position);
                if (position < mList.size()) {
                   // Log.i(TAG, "onClick: List Size"+mList.size());
                    if (position == 1){
                        if (Build.VERSION.SDK_INT >= 22){
                            addPermissionForStorage(position);
                            //Log.i(TAG, "onClick: permission run"+position);
                        }
                        else {
                            position++;
                            screenPager.setCurrentItem(position);
                        }

                    }
                    else {
                        position++;
                        screenPager.setCurrentItem(position);
                    }

                }

                if (position == mList.size()-1) { // when we rech to the last screen
                    loaddLastScreen();
                }
                else {
                    btnGetStarted.setVisibility(View.GONE);
                    btnNext.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.VISIBLE);
                    tabIndicator.setVisibility(View.VISIBLE);
                }

            }
        });

        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }
                else {
                    btnGetStarted.setVisibility(View.GONE);
                    btnNext.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.VISIBLE);
                    tabIndicator.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent mainActivity = new Intent(getApplicationContext(),RegisterScreen.class);
                startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                savePrefsData();
                finish();



            }
        });

        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });
    }

    private void addPermissionForStorage(int position) {
        if (ContextCompat.checkSelfPermission(IntroActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(IntroActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(IntroActivity.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(IntroActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
            }
        }else {
            position++;
            screenPager.setCurrentItem(position);
        }
        position++;
        screenPager.setCurrentItem(position);
    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }
}