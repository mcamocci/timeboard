package com.haikarose.time.activities;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.haikarose.time.R;
import com.haikarose.time.fragments.AboutFragment;
import com.haikarose.time.fragments.OnlyFragment;
import com.haikarose.time.fragments.NavigationFragment;
import com.haikarose.time.pojos.TimeItem;
import com.haikarose.time.tools.ConnectionAvaillable;
import com.haikarose.time.tools.DataGenerator;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnlyFragment.StartDetailActivityListener,NavigationFragment.menuItemListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private OnlyFragment fragment;
    private InterstitialAd interstitialAd;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course=DataGenerator.getCourse(getBaseContext());

        if(Build.VERSION.SDK_INT>=21){
            // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        if(!(ConnectionAvaillable.isInternetConnected(getBaseContext()))){
            Intent intent=new Intent(getBaseContext(),NoConnectionActivity.class);
            startActivity(intent);
            finish();
        }

        DataGenerator.setDayOfWeek(getBaseContext(),Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        fragment=new OnlyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();


        toolbar=(Toolbar)findViewById(R.id.toolbar);

        if(Integer.parseInt(DataGenerator.getDayOfWeek(getBaseContext()))==2){

            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Monday "+course+"</font>"));

        }else if(Integer.parseInt(DataGenerator.getDayOfWeek(getBaseContext()))==3){

            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Tuesday "+course+"</font>"));

        }else if(Integer.parseInt(DataGenerator.getDayOfWeek(getBaseContext()))==4){

            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Wednesday "+course+"</font>"));

        }else if(Integer.parseInt(DataGenerator.getDayOfWeek(getBaseContext()))==5){

            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Thursday "+course+"</font>"));

        }else if(Integer.parseInt(DataGenerator.getDayOfWeek(getBaseContext()))==6){

            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Friday "+course+"</font>"));

        }

        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);


        //////////////////////the interstitial ad goes here//////


        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstial_id));
        AdRequest request=new AdRequest.Builder().build();
        interstitialAd.loadAd(request);

        //the banner ad
        AdView adds=(AdView)findViewById(R.id.adView);
        adds.loadAd(request);
        //the banner ad end

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
            }

        });


        ///////////////////////////////////////////////////////

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };

        drawerLayout.addDrawerListener(toggle);

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                toggle.syncState();
            }
        });

        ///////////////////////////////////////////////////////

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        if(id==android.R.id.home){

        }else if(id==R.id.about){
            AboutFragment fragment=new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                    .commit();
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>About 2</font>"));
        }

        return true;
    }

    @Override
    public void getSelectedItem(int position) {
        drawerLayout.closeDrawers();
        if(position==0){
            DataGenerator.setDayOfWeek(getBaseContext(),2);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Monday "+course+"</font>"));
        }else if(position==1){
            DataGenerator.setDayOfWeek(getBaseContext(),3);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Tuesday "+course+"</font>"));
        }else if(position==2){
            DataGenerator.setDayOfWeek(getBaseContext(),4);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Wednesday "+course+" </font>"));
        }else if(position==3){
            DataGenerator.setDayOfWeek(getBaseContext(),5);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Thursday "+course+"</font>"));
        }else if(position==4){
            DataGenerator.setDayOfWeek(getBaseContext(),6);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>Friday "+course+"</font>"));
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new OnlyFragment())
                .commitNow();


        fragment.updateFragmentInfo(position,getBaseContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStartedActivity(TimeItem timeItem,LinearLayout layout) {

        Intent intent=new Intent(getBaseContext(), DetailActivity.class);
        Bundle bundle=TimeItem.toBundle(timeItem);
        intent.putExtras(bundle);
        Bundle options=ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                layout,"transition").toBundle();

        if(Build.VERSION.SDK_INT>=16){
            startActivity(intent,options);
        }else{
            startActivity(intent);
        }

        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!(ConnectionAvaillable.isInternetConnected(getBaseContext()))){
            Intent intent=new Intent(getBaseContext(),NoConnectionActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
