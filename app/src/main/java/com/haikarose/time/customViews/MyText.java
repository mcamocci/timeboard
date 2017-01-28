package com.haikarose.time.customViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by root on 12/17/16.
 */

public class MyText extends TextView {

    private MyEvent event;

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }

    public MyText(Context context){
        super(context);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        if(event!=null){
            event.say("am responding too!  haa");
        }
    }

    public void setEvent(MyEvent event){
        this.event=event;
    }


    public interface MyEvent{
        void say(String something);
    }


}
