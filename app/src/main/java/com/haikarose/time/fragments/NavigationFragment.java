package com.haikarose.time.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikarose.time.R;
import com.haikarose.time.adapters.MenuItemAdapter;
import com.haikarose.time.pojos.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class NavigationFragment extends Fragment {


    public menuItemListener listener;
    private List<MenuItem> menuItemList;
    private RecyclerView recyclerView;
    private MenuItemAdapter adapter;

    public NavigationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_navigation, container, false);


        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        menuItemList=new ArrayList<>();
        menuItemList.add(new MenuItem("Monday"));
        menuItemList.add(new MenuItem("Tuesday"));
        menuItemList.add(new MenuItem("Wednesday"));
        menuItemList.add(new MenuItem("Thursday"));
        menuItemList.add(new MenuItem("Friday"));

        adapter=new MenuItemAdapter(menuItemList,listener);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(menuItemListener) context;
    }

    public interface menuItemListener{
        public void getSelectedItem(int position);
    }

}
