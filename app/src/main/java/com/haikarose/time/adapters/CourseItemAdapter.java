package com.haikarose.time.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haikarose.time.R;
import com.haikarose.time.activities.MainActivity;
import com.haikarose.time.pojos.CourseItem;
import com.haikarose.time.tools.DataGenerator;

import java.util.List;

/**
 * Created by root on 10/27/16.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.MenuHolder> {

    private List<CourseItem> items;


    public CourseItemAdapter(List<CourseItem> itemList){
        this.items=itemList;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        MenuHolder holder=new MenuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        CourseItem item=items.get(position);
        holder.setData(item);
    }

    public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView menuName;
        private TextView menuShort;

        //private TextView year;
        //private TextView college;

        private View view;
        private CourseItem item;

        public MenuHolder(View view){
            super(view);
            this.view=view;
            view.setOnClickListener(this);
            //year=(TextView)view.findViewById(R.id.year);
            menuName=(TextView)view.findViewById(R.id.name);
            menuShort=(TextView)view.findViewById(R.id.menu_text_short);
           // college=(TextView)view.findViewById(R.id.college);
        }

        public void setData(CourseItem item){
            menuName.setText(item.getLongForm());
            menuShort.setText((item.getAbbreviation().toUpperCase()));
           // college.setText(item.getCollege().toUpperCase());
           // year.setText("Year : "+item.getAbbreviation().substring(item.getAbbreviation().length()-1,item.getAbbreviation().length()));
            this.item=item;
        }

        public void onClick(View view){
            DataGenerator.setCourse(view.getContext(),item.getAbbreviation());
            Intent intent=new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);

        }
    }
}
