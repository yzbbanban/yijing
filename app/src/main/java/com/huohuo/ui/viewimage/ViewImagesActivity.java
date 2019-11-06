/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huohuo.ui.viewimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dian.commonlib.base.BaseActivity;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class ViewImagesActivity extends BaseActivity implements PhotoViewAttacher.OnViewTapListener {
	
	public static final int MORE_REQUEST = Activity.RESULT_CANCELED + 0xF1;
	public static final int REPORT_REQUEST = Activity.RESULT_CANCELED + 0xF2;
	
	public final static String UID = "uid";
	public final static String DID = "did";
	public final static String IMAGES = "images";
	public final static String SHOW_INDEX = "showIndex";
	
	private ImagePagerAdapter mAdapter;	
	private ViewPager mPager;
//	private TextView pageTextView;
//	private LinearLayout littlePoint;
//	private ImageView[] mDots;
	
	private String[] images;
	private int showIndex;

	@Override
	public int getLayoutId() {
		return R.layout.activity_view_image;
	}

	@Override
	public void initViewAndData() {
		images = this.getIntent().getStringArrayExtra(IMAGES);
		showIndex = this.getIntent().getIntExtra(SHOW_INDEX, 0);
		initView();
	}

	public void onMoreButtonClick(View v){
		
	}
	
	private void initView(){
		mAdapter = new ImagePagerAdapter(getSupportFragmentManager(),images, this);
		mPager = (ViewPager) findViewById(R.id.pager);
//		pageTextView = (TextView) findViewById(R.id.pageTextView);
//		littlePoint = (LinearLayout) findViewById(R.id.little_point);
//		initDots();
		mPager.setAdapter(mAdapter);
		 
//		mPager.setOnPageChangeListener(new OnPageChangeListener() {
//			@Override
//			public void onPageScrollStateChanged(int arg0) {
//			}
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//			}
//			@Override
//			public void onPageSelected(int position) {
//				Log.i("Icache", "onPageSelected = "+position);
//				setCurrentDot(position);
//				updatePageInfo();
//				showIndex = position;
//			}
//		});
//
//		mPager.setCurrentItem(showIndex);
//		updatePageInfo();
	}
//	private void initDots() {
//		mDots = new ImageView[images.length];
//		littlePoint.removeAllViews();
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//		int margin = DensityUtils.dp2px(App.context, 8);
//		params.setMargins(margin, margin, margin, margin);
//		for (int i = 0; i < images.length; i++) {
//			ImageView iv = new ImageView(this);
//			iv.setImageResource(R.drawable.see_big_pic_dot_normal);
////			mDots[i] = (ImageView) View.inflate(this, R.layout.dot_imageview, null);
//			mDots[i] = iv;
//			littlePoint.addView(mDots[i], params);
//
//		}
//		mDots[showIndex].setImageResource(R.drawable.see_big_pic_dot_selected);
//	}
//	private void setCurrentDot(int currentPosition) {
//
//		if (showIndex != currentPosition) {
//			showIndex = currentPosition;
//			for (int i = 0; i < mDots.length; i++) {
//				if (i == currentPosition) {
//					mDots[i].setImageResource(R.drawable.see_big_pic_dot_selected);
//				} else {
//					mDots[i].setImageResource(R.drawable.see_big_pic_dot_normal);
//				}
//			}
//		}
//	}
//
//	private void updatePageInfo(){
//		pageTextView.setText(String.format("%d / %d", showIndex+1, images.length));
//	}
	
	//轻触退出查看页
	@Override
	public void onViewTap(View view, float x, float y) {
		// TODO Auto-generated method stub
		this.finish();
	}
	
	public boolean saveBitmap(Bitmap bitmap, String filePath){
		if(bitmap != null){
			 File file = new File(filePath);
	         if (!file.exists()) {	             
	         	try {	             	
               	 file.createNewFile();
           		 final OutputStream outStream = new FileOutputStream(file);
           	 
           		 bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
           		 outStream.flush();
           		 outStream.close();         
	            } catch (Exception e) {
	            	e.printStackTrace();
	               return false;
	            }
	         }         
		}		
		return true;
	}
	@Override
	public void finish() {
		setResult(RESULT_OK, null);
		super.finish();
	}
}
