package com.example.parallaxviewpager;

import java.util.ArrayList;
import java.util.List;

import com.andraskindler.parallaxviewpager.ParallaxViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;

public class MainActivity extends FragmentActivity {
	

	static int[] imageUrls = new int[] { 
			R.drawable.fg_intro_1, R.drawable.fg_intro_2,
			R.drawable.fg_intro_3,R.drawable.fg_intro_4};
	
	
	private List<ImageView> imageViewIndicatorList = null;
	private LinearLayout linearLayoutIndicator;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    ParallaxViewPager parallaxViewPager = (ParallaxViewPager) findViewById(R.id.viewPager);
	    parallaxViewPager.setBackgroundResource(R.drawable.bg_intro);
	    PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
	    parallaxViewPager.setAdapter(adapter);
	    linearLayoutIndicator = (LinearLayout) findViewById(R.id.indicator_ll);
	    parallaxViewPager.addOnPageChangeListener(new IPageChangeListener());
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
	
	
	
	public static class PlaceholderFragment extends Fragment {

		private static final String EXTRA_POSITION = "EXTRA_POSITION";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			int position = getArguments().getInt(EXTRA_POSITION);
			RelativeLayout localRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_intro_page, container, false);
			
			ImageView image = (ImageView)localRelativeLayout.findViewById(R.id.image);
			
			image.setImageResource(imageUrls[position]);
			
			return localRelativeLayout;
		}

	}

	private static final class PageAdapter extends FragmentStatePagerAdapter {

		public PageAdapter(android.support.v4.app.FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			final Bundle bundle = new Bundle();
			bundle.putInt(PlaceholderFragment.EXTRA_POSITION, position);

			final PlaceholderFragment fragment = new PlaceholderFragment();
			fragment.setArguments(bundle);

			return fragment;
		}

		@Override
		public int getCount() {
			return imageUrls.length;
		}

	}

}
