package com.haikarose.time.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haikarose.time.R;
import com.haikarose.time.adapters.CourseItemAdapter;
import com.haikarose.time.pojos.CourseItem;
import com.haikarose.time.tools.CommonInformation;
import com.haikarose.time.tools.ConnectionAvaillable;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class StartActivity extends AppCompatActivity {

    private TextView content_status;
    private RecyclerView recyclerView;
    private CourseItemAdapter adapter;
    private List<CourseItem> courseItemList;
    private View retry_view;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(!(ConnectionAvaillable.isInternetConnected(getBaseContext()))){
            Intent intent=new Intent(getBaseContext(),NoConnectionActivity.class);
            startActivity(intent);
            finish();
        }

        content_status=(TextView)findViewById(R.id.content_status);
        retry_view=findViewById(R.id.retry_view);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        courseItemList=new ArrayList<>();


        performNetworkOperation("ifm");


        adapter=new CourseItemAdapter(courseItemList);
        recyclerView.setAdapter(adapter);
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


    public void performNetworkOperation(final String college){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("college", college.toLowerCase());


        client.setConnectTimeout(15000);
        client.setTimeout(15000);

        client.get(getBaseContext(),CommonInformation.GET_COURSE_URL,params, new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                progressBar.setVisibility(View.VISIBLE);
                retry_view.setVisibility(View.GONE);
                content_status.setText("Loading courses..");
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type listType =new TypeToken<ArrayList<CourseItem>>(){}.getType();
                courseItemList=new Gson().fromJson(responseString,listType);
                adapter=new CourseItemAdapter(courseItemList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                retry_view.setVisibility(View.GONE);
                content_status.setText("Select course");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressBar.setVisibility(View.INVISIBLE);
                retry_view.setVisibility(View.VISIBLE);

                content_status.setText("Failed to load");

                retry_view.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        performNetworkOperation(college);
                    }

                });
            }
        });
    }




}
