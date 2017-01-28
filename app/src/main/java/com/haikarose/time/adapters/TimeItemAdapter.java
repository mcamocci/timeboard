package com.haikarose.time.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haikarose.time.R;
import com.haikarose.time.fragments.OnlyFragment;
import com.haikarose.time.pojos.TimeItem;

import java.util.List;

/**
 * Created by root on 10/23/16.
 */

public class TimeItemAdapter extends RecyclerView.Adapter<TimeItemAdapter.ItemViewHolder> {

    List<TimeItem> itemList;
    OnlyFragment.StartDetailActivityListener listener;

    public TimeItemAdapter(List<TimeItem> myItems,OnlyFragment.StartDetailActivityListener listener){
        this.listener=listener;
        this.itemList=myItems;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item,parent,false);
        ItemViewHolder holder=new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        TimeItem item=itemList.get(position);
        holder.setData(item,listener);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView time;
        private TextView code;
        private TextView name;
        private Context context;
        private TextView shorten;
        private View view;
        private OnlyFragment.StartDetailActivityListener listener;

        public ItemViewHolder(View view){

            super(view);
            view.setOnClickListener(this);
            this.view=view;
            time=(TextView)view.findViewById(R.id.time);
            code=(TextView)view.findViewById(R.id.code);
            name=(TextView)view.findViewById(R.id.name);
            shorten=(TextView)view.findViewById(R.id.shorten);
            this.context=view.getContext();

        }
        public void setData(TimeItem item,OnlyFragment.StartDetailActivityListener listener){

            this.listener=listener;

            if(item.getType().equalsIgnoreCase(TimeItem.TYPE_TUT)){

                time.setTextColor(context.getResources().getColor(R.color.red));
                time.setText(item.getTime());
                code.setText(item.getCode());
                name.setText(item.getName());
                shorten.setText(item.getType().substring(0,1).toUpperCase());

            }else{

                //time.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
                time.setText(item.getTime());
                code.setText(item.getCode());
                name.setText(item.getName());
                shorten.setText(item.getType().substring(0,1).toUpperCase());
            }

        }

        @Override
        public void onClick(View v) {
            view.setSelected(true);
            LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.first_box);
            listener.onStartedActivity(itemList.get(getAdapterPosition()),linearLayout);
        }
    }
}
