package com.android.qbpeopledirectory.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.widget.*;

import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;

public class CurrentUserFragment extends Fragment{
    TextView currentUserTV,designationTV,locationTV,myEmailTV,myPhoneTV,myDobTV,mySkillTV,myAddressTV;

    public CurrentUserFragment(){


        if(ParseUser.getCurrentUser()==null){

            LogInFragment newFragment=new LogInFragment();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

        }
    }
    /* (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    ParseFile imageFile;
    private ImageView imageUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.current_user_layout, container, false);
        Parse.initialize(getActivity().getBaseContext(), "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");

        currentUserTV=(TextView) view.findViewById(R.id.firstLastNameTV);
        designationTV=(TextView) view.findViewById(R.id.myDesignationTV);
        locationTV=(TextView) view.findViewById(R.id.myLocationTV);
        myEmailTV=(TextView) view.findViewById(R.id.myEmailTV);
        myPhoneTV=(TextView) view.findViewById(R.id.myPhoneTV);
        myDobTV=(TextView) view.findViewById(R.id.myDobTV);
        mySkillTV=(TextView) view.findViewById(R.id.mySkillTV);
        myAddressTV=(TextView) view.findViewById(R.id.myAddressTV);


        ParseUser currentUser=ParseUser.getCurrentUser();




        //Checks for current user
        if(currentUser!=null){
            String currentUserName=(String) currentUser.getUsername();
            currentUserTV.setText(currentUser.get("firstName").toString()+" "+currentUser.get("lastName"));
		/*currentUser.setText(ParseUser.getCurrentUser().getUsername().toString());*/
            getActivity().getActionBar().setTitle(currentUser.getUsername().toString()+"'s Profile");
            designationTV.setText(currentUser.get("designation").toString());
            locationTV.setText(currentUser.get("location").toString());
            myEmailTV.setText(currentUser.getEmail());
            myPhoneTV.setText(currentUser.get("phone").toString());
            myDobTV.setText(currentUser.get("dateOfBirth").toString());
            mySkillTV.setText(currentUser.get("skill").toString());
            myAddressTV.setText(currentUser.get("address").toString());
            imageFile=(ParseFile) currentUser.get("image");

            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {

                    if(e==null){
                        ImageView image = (ImageView) getActivity().findViewById(R.id.imageUser);

                        image.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length) );
                    }

                }
            });




        }
        else{
            LogInFragment newFragment=new LogInFragment();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
        return view;
    }

}