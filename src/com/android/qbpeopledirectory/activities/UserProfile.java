package com.android.qbpeopledirectory.activities;

import java.util.List;
import java.util.Queue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UserProfile extends Fragment{
    private String FirstName,LastName, Designation,Location,Skills,DOB,PhoneNo,Address,Email;
    ParseImageView profilePic;
    ParseFile imageFile;
    private String SelectedUser;
    ImageButton share;
    /* final ProgressDialog dlg =new ProgressDialog(
             getActivity().getBaseContext());*/
    public UserProfile(String username) {

         /*this.dlg.setTitle("Please wait.");
         this.dlg.setMessage("Loading profile.");
         this.dlg.show();*/

        this.SelectedUser=username;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {







        View view=inflater.inflate(R.layout.user_profile_layout, container, false);





        TextView firstTV = (TextView) view.findViewById(R.id.textView1);
        TextView lastTV = (TextView) view.findViewById(R.id.textView2);
        TextView skillsTV = (TextView) view.findViewById(R.id.textView6);
        TextView designationTV = (TextView) view.findViewById(R.id.textView3);
        TextView phoneTV = (TextView) view.findViewById(R.id.textView9);
        TextView locationTV = (TextView) view.findViewById(R.id.textView7);
        TextView addressTV = (TextView) view.findViewById(R.id.textView12);
        TextView emailTV=(TextView) view.findViewById(R.id.textView10);
        Parse.initialize(getActivity().getBaseContext(), "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", SelectedUser);
        try {
            List<ParseUser>current=query.find();
            FirstName=(String) current.get(0).get("firstName");
            LastName=(String) current.get(0).get("lastName");
            Designation=(String) current.get(0).get("designation");
            Skills=(String) current.get(0).get("skill");
            Location=(String) current.get(0).get("location");
            PhoneNo=(String) current.get(0).get("phone");
            Address=(String) current.get(0).get("address");
            Email=(String) current.get(0).getEmail();
            imageFile=(ParseFile) current.get(0).get("image");

            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {

                    if(e==null){
                        ImageView image = (ImageView) getActivity().findViewById(R.id.imageView1);

                        image.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length) );
                    }

                }
            });
                /*dlg.dismiss();*/
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }





        firstTV.setText(FirstName);
        lastTV.setText(LastName);
        skillsTV.setText(Skills);
        designationTV.setText(Designation);
        phoneTV.setText(PhoneNo);
        locationTV.setText(Location);
        addressTV.setText(Address);
        emailTV.setText(Email);



        share=(ImageButton) view.findViewById(R.id.imageButtonShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                getActivity().startActivity(Intent.createChooser(sharingIntent, "Share using"));



            }
        });





        return view;
    }

}