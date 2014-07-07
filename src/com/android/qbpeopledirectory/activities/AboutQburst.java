package com.android.qbpeopledirectory.activities;

import android.app.ProgressDialog;
import com.android.qbpeopledirectory.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class AboutQburst extends Fragment {

	public AboutQburst() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity().getActionBar().setTitle("About QBurst");
       /* final ProgressDialog dlg = new ProgressDialog(
                getActivity());
        dlg.setTitle("Please wait.");
        dlg.setMessage("Signing in.  Please wait.");
        dlg.show();*/
		final View view = inflater.inflate(R.layout.about_qburst_layout,
                container, false);


		WebView webQburst=(WebView) view.findViewById(R.id.webView1);
		webQburst.getSettings().setJavaScriptEnabled(true);
		webQburst.loadUrl("http://www.qburst.com");

        if(webQburst.getProgress()==100){
            /*dlg.dismiss();*/
        }
		return view;
	}

}
