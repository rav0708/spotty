package com.example.spotty;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class adapter extends PagerAdapter{


    private Context ctx1;
    private int[] ImageArray=new int [] {R.drawable.room1,R.drawable.room2,R.drawable.room3,R.drawable.room12,R.drawable.cooperate3,R.drawable.room4
            ,R.drawable.room5, R.drawable.office10,R.drawable.room14,R.drawable.kitchen5,R.drawable.room11,R.drawable.office62,R.drawable.room6,R.drawable.room8};


    adapter(Context context){
        ctx1=context;
    }

    @Override
    public int getCount() {
        return ImageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(ctx1);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(ImageArray[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
