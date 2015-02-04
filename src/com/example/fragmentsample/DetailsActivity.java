package com.example.fragmentsample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FrameLayout layout = new FrameLayout(this);
		layout.setId(R.id.details);
		setContentView(layout);
		
		String cont = "";
		Bundle extras = getIntent().getExtras();
		cont = extras.getString("contents");
		
		DetailsFragment fragment = DetailsFragment.newInstance(cont);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.details, fragment);
		ft.commit();
	}
	 public static class DetailsFragment extends Fragment{
		 public static DetailsFragment newInstance(String cont){
			 DetailsFragment fragment = new DetailsFragment();
			 Bundle bundle = new Bundle();
			 bundle.putString("contents", cont);
			 fragment.setArguments(bundle);
			 return fragment;
		 }

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if(container == null) return null;
			TextView tv = new TextView(getActivity());
			tv.setTextSize(20);
			tv.setText(getArguments().getString("contents"));
			return tv;
		}
	 }

}
