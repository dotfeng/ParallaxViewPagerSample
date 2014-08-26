package com.example.parallaxviewpager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jakewharton.salvage.RecyclingPagerAdapter;

public class GuideAdapter extends RecyclingPagerAdapter {

	  int[] introResIds;
	
	  Context context;
	public GuideAdapter(int[] introResIds, Context context){
		this.introResIds = introResIds;
		this.context = context;
	}
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return introResIds.length;
	}
	  public static int dp2px(Context paramContext, float paramFloat)
	  {
	    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
	    return (int)TypedValue.applyDimension(1, paramFloat, localDisplayMetrics);
	  }

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		// TODO Auto-generated method stub
		ImageView localImageView;
		if(convertView== null){
	    	RelativeLayout localRelativeLayout = new RelativeLayout(context);
	        localRelativeLayout.setGravity(0);
	        int i = dp2px(context, 100.0F);
	        localRelativeLayout.setPadding(0, 0, 0, i);
	        
	        localImageView = new ImageView(context);
	        ImageView.ScaleType localScaleType = ImageView.ScaleType.CENTER;
	        localImageView.setScaleType(localScaleType);
//	        imageLoader.displayImage("drawable://" + introResIds[position], localImageView, options);
	        localImageView.setImageResource(introResIds[position]);
	        localRelativeLayout.addView(localImageView, -1, -1);
	        convertView = localRelativeLayout;
		}
        return convertView;
	}
}
