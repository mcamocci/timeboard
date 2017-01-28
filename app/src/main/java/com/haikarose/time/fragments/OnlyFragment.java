package com.haikarose.time.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haikarose.time.R;
import com.haikarose.time.adapters.TimeItemAdapter;
import com.haikarose.time.pojos.TimeItem;
import com.haikarose.time.tools.CommonInformation;
import com.haikarose.time.tools.DataGenerator;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
public class OnlyFragment extends Fragment {


    private StartDetailActivityListener listener;
    private TimeItemAdapter adapter;
    private List<TimeItem> list;
    private RecyclerView recyclerView;
    private List<TimeItem> timeItems;
    private ProgressBar progressBar;
    private View retry_view;

    public OnlyFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_monday, container, false);

        retry_view=(View)view.findViewById(R.id.retry);
        retry_view.setVisibility(View.GONE);


        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        progressBar=(ProgressBar)view.findViewById(R.id.progress);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //the network issue is solved here
        performNetworkOperation(Integer.parseInt(DataGenerator.getDayOfWeek(getContext())),view.getContext());


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(StartDetailActivityListener)context;
    }


    public void updateFragmentInfo(int day,Context context){
            performNetworkOperation(day,context);
    }


    public interface StartDetailActivityListener{

        void onStartedActivity(TimeItem timeItem, LinearLayout layout);

    }


    public void performNetworkOperation(final int day, final Context context){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("course",DataGenerator.getCourse(context));
        params.put("day",Integer.toString(day));

        client.setConnectTimeout(15000);
        client.setTimeout(15000);

        client.get(CommonInformation.GET_DATA_URL,params, new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                progressBar.setVisibility(View.VISIBLE);
                retry_view.setVisibility(View.GONE);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type listType =new TypeToken<ArrayList<TimeItem>>(){}.getType();
                timeItems=new Gson().fromJson(responseString,listType);
                // timeItems= DataGenerator.mondayItems();
                Collections.sort(timeItems, new Comparator<TimeItem>() {
                    @Override
                    public int compare(TimeItem o1, TimeItem o2) {
                        return o1.getTime().compareTo(o2.getTime());
                    }
                });
                adapter=new TimeItemAdapter(timeItems,listener);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                retry_view.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressBar.setVisibility(View.INVISIBLE);
                retry_view.setVisibility(View.VISIBLE);

                retry_view.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        performNetworkOperation(day,context);
                    }

                });
            }
        });
    }

}
