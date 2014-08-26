package com.example.parallaxviewpager;


import java.util.ArrayList;
import java.util.List;

import com.andraskindler.parallaxviewpager.ParallaxViewPager;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.app.Activity;

public class MainActivityAdapter extends Activity{
	ParallaxViewPager viewPager;
	GuideAdapter vpAdapter;
	
	

	int[] imageUrls = new int[] { 
			R.drawable.fg_intro_1, R.drawable.fg_intro_2,
			R.drawable.fg_intro_3,R.drawable.fg_intro_4};
	
	
	private List<ImageView> imageViewIndicatorList = null;
	private LinearLayout linearLayoutIndicator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		viewPager = (ParallaxViewPager) findViewById(R.id.viewPager);
//		pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		linearLayoutIndicator = (LinearLayout) findViewById(R.id.indicator_ll);
		//给viewPager进行配置
		vpAdapter = new GuideAdapter(imageUrls, this);
		viewPager.setAdapter(vpAdapter);
		viewPager.addOnPageChangeListener(new IPageChangeListener());
//		pageIndicator.setViewPager(viewPager);
		initIndicator();
	}
	
	
	private void initIndicator() {
		imageViewIndicatorList = new ArrayList<ImageView>();
		for (int i = 0; i < imageUrls.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(new LayoutParams(80, 10));
			iv.setPadding(5, 0, 5, 0);
			iv.setScaleType(ScaleType.FIT_XY);
			if (i == 0) {
				// 默认选中第一张图片
				iv.setImageResource(R.drawable.page_indicator_focused);
			} else {
				iv.setImageResource(R.drawable.page_indicator);
			}
			imageViewIndicatorList.add(iv);
			linearLayoutIndicator.addView(iv);
		}
	}
	
	
	
	class IPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageSelected(int position) {
			// 更改 小圆点 指示器
			for (int i = 0; i < imageViewIndicatorList.size(); i++) {
				if (position == i) {
					imageViewIndicatorList.get(i).setImageResource(
							R.drawable.page_indicator_focused);
				} else {
					imageViewIndicatorList.get(i).setImageResource(
							R.drawable.page_indicator);
				}
			}
		}
	}
}
