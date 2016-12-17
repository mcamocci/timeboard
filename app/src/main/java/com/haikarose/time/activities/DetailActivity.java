package com.haikarose.time.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.haikarose.time.R;
import com.haikarose.time.pojos.TimeItem;


public class DetailActivity extends AppCompatActivity {

    private TextView instructor;
    private TextView code;
    private TextView subject;
    private TextView time;
    private TextView venue;
    private TextView type;

    private ImageView back;

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        getSupportActionBar().hide();


        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstial_id));
        AdRequest request=new AdRequest.Builder().build();
        interstitialAd.loadAd(request);

        interstitialAd.show();


        back=(ImageView)findViewById(R.id.back);
        venue=(TextView)findViewById(R.id.venue);
        time=(TextView)findViewById(R.id.time);
        instructor=(TextView)findViewById(R.id.instructor);
        code=(TextView)findViewById(R.id.code);
        type=(TextView)findViewById(R.id.type);
        subject=(TextView)findViewById(R.id.subject);


        TimeItem item=TimeItem.fromBundle(getIntent().getExtras());
        venue.setText("Venue : "+item.getVenue());
        instructor.setText("Instructor : "+item.getInstructor());
        code.setText("Code : "+item.getCode());
        subject.setText("Subject : "+item.getName());
        time.setText("Time : "+item.getTime());

        if(item.getType().equalsIgnoreCase("t")){
            type.setText("tutorial".toUpperCase());
        }else{
            type.setText("Lecture".toUpperCase());
        }


        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
