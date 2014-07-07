package com.android.qbpeopledirectory.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.widget.*;
import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import com.parse.entity.mime.content.StringBody;

public class SearchResults extends Fragment{
    ListView L;
    ImageView profile_pic;
    List<RowItem> rowItems;
    TextView firstTV,lastTV,designationTV;
    String[] S={"rock","mock"};
    ArrayList<String> userNames;
    String designation,location,skills;

    ParseQuery<ParseUser> query;
    public SearchResults(String designation,String location,String skills) {
        // TODO Auto-generated constructor stub
        this.designation=designation;
        this.location=location;
        this.skills=skills;

    }

    /* (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Parse.initialize(getActivity().getBaseContext(), "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");


        View view=inflater.inflate(R.layout.search_results_layout, container, false);

        firstTV=(TextView) view.findViewById(R.id.firstTV);
        lastTV=(TextView) view.findViewById(R.id.lastTV);
        designationTV=(TextView) view.findViewById(R.id.desigTV);

        L=(ListView) view.findViewById(R.id.searchLV);

        rowItems = new ArrayList<RowItem>();
        userNames = new ArrayList<String>();

        query = ParseUser.getQuery();

        if(!designation.equals("Choose one. ."))
            query.whereEqualTo("designation",designation);

        if(!location.equals("Choose one. ."))
            query.whereEqualTo("location",location);


        if(!skills.equals("Choose one. ."))
            query.whereEqualTo("skill",skills);


        final ProgressDialog dlg = new ProgressDialog(
                getActivity());
        dlg.setTitle("Please wait.");
        dlg.setMessage("Searching.  Please wait.");
        dlg.show();



        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                ParseUser u=new ParseUser();
                for (int i = 0; i < objects.size();i++) {
                    try {

                        u = (ParseUser)objects.get(i).fetch();

                        userNames.add(u.getUsername());
                        String firstname = u.getString("firstName").toString();
                        String lastname = u.getString("lastName").toString();
                        String designation = u.getString("designation").toString();





                        RowItem item = new RowItem(firstname,
                                u.getParseFile("image"), lastname,
                                designation);
                        rowItems.add(item);
                        Toast.makeText(getActivity().getBaseContext(), firstname, Toast.LENGTH_SHORT).show();

                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
                CustomUserAdapter adapter = new CustomUserAdapter(getActivity().getBaseContext(), rowItems);
                L.setAdapter(adapter);

                dlg.dismiss();
                if(L.getCount()==0)
                    Toast.makeText(getActivity().getBaseContext(),"Sorry no results found.",Toast.LENGTH_SHORT).show();
            } });
        L.setOnItemClickListener(new OnItemClickListener(){



            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub


                String usr=userNames.get(position).toString();

                Toast.makeText(getActivity().getBaseContext(),usr, Toast.LENGTH_SHORT).show();



                UserProfile newFragment = new UserProfile(usr);
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }




        });




        return view;

    }




}