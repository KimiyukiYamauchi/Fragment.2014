package com.example.fragmentsample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public static class PageListFragment extends ListFragment{
		
		boolean mTwoPane;
		int mCurCheckPosition = 0;
		
		public static final String[][] PAGELIST = {
			{"タイトル", "河口湖　伊藤左千夫"},
			{"ページ1", "ページ1の内容"},
			{"ページ2", "ページ2の内容"},
			{"ページ3", "ページ3の内容"},
			{"ページ4", "ページ4の内容"},
		};

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			String contents = PAGELIST[position][1];
			showDetails(contents);
		}
		
		void showDetails(String contents){
			if(mTwoPane){
				DetailsActivity.DetailsFragment fragment =
						DetailsActivity.DetailsFragment.newInstance(contents);
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, fragment);
				ft.commit();
			}else{
				Intent it = new Intent();
				it.setClass(getActivity(), DetailsActivity.class);
				it.putExtra("contents", contents);
				startActivity(it);
			}
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			List<String> alist = new ArrayList<String>();
			for(String[] pagelist : PAGELIST){
				alist.add(pagelist[0]);
			}
			
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_activated_1, alist));
			
			View detailsFrame = getActivity().findViewById(R.id.details);
			mTwoPane = detailsFrame != null && 
					detailsFrame.getVisibility() == View.VISIBLE;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
