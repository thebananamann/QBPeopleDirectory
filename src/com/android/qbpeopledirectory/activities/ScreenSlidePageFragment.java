package com.android.qbpeopledirectory.activities;


import java.io.ByteArrayOutputStream;
import java.util.List;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenSlidePageFragment extends Fragment {
	private float x1,x2;
	static final int MIN_DISTANCE = 150;
	TextView tv;
	Button bt;
	static int i;
	String name;
    ImageView imageUser;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	 Parse.initialize(getActivity().getBaseContext(), "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");
			
    	View view=inflater.inflate(R.layout.employee_layout, container, false);
    	tv=(TextView) view.findViewById(R.id.textView1);
    bt=(Button) view.findViewById(R.id.button1);
       
       
    	tv.setText("nothing happens");
    	ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.orderByAscending("username");



    
	
    	
        return view;
    }
  
}


