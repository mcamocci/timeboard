package com.haikarose.time.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haikarose.time.R;
import com.haikarose.time.fragments.NavigationFragment;
import com.haikarose.time.pojos.MenuItem;
import com.haikarose.time.tools.DataGenerator;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by root on 10/27/16.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuHolder> {

    private List<MenuItem> items;
    private NavigationFragment.menuItemListener listener;


    public MenuItemAdapter(List<MenuItem> itemList, NavigationFragment.menuItemListener listener){
        this.items=itemList;
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item,parent,false);
        MenuHolder holder=new MenuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {

        MenuItem item=items.get(position);
        holder.setData(item,listener);
    }

    public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView menuName;
        private TextView menuShort;
        private NavigationFragment.menuItemListener listener;

        public MenuHolder(View view){
            super(view);
            view.setOnClickListener(this);
            menuName=(TextView)view.findViewById(R.id.menu_text);
            menuShort=(TextView)view.findViewById(R.id.menu_text_short);
        }

        public void setData(MenuItem item,NavigationFragment.menuItemListener listener){
            this.listener=listener;
            menuName.setText(item.getName());
            menuShort.setText((item.getName().substring(0,1)));
        }

        public void onClick(View view){
            listener.getSelectedItem(MenuHolder.this.getAdapterPosition());
        }
    }
}
