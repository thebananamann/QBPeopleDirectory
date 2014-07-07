package com.android.qbpeopledirectory.activities;

import android.app.ProgressDialog;
import com.android.qbpeopledirectory.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchForUser extends Fragment{

    Spinner spinnerSkills,spinnerDesignation,spinnerLocation;

    Button searchBT;
    Spinner designationSP,skillSP,locationSP;
    /* (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.search_user_layout, container, false);

        getActivity().getActionBar().setTitle("Search");

        spinnerSkills = (Spinner) view.findViewById(R.id.spinnerSkills);
        ArrayAdapter<CharSequence> adapterSkills = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.skills, android.R.layout.simple_spinner_item);
        adapterSkills.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSkills.setAdapter(adapterSkills);


        spinnerLocation = (Spinner) view.findViewById(R.id.spinnerLocation);
        ArrayAdapter<CharSequence> adapterLocation = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.location, android.R.layout.simple_spinner_item);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapterLocation);

        spinnerDesignation = (Spinner) view.findViewById(R.id.spinnerDesignation);
        ArrayAdapter<CharSequence> adapterDesignation = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.designation, android.R.layout.simple_spinner_item);
        adapterDesignation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesignation.setAdapter(adapterDesignation);
        searchBT=(Button) view.findViewById(R.id.searchBT);
        searchBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Fragment newFragment;
                newFragment=new SearchResults(spinnerDesignation.getSelectedItem().toString(),spinnerLocation.getSelectedItem().toString(),spinnerSkills.getSelectedItem().toString());
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();


            }
        });
        return view;
    }

    public SearchForUser() {
        // TODO Auto-generated constructor stub



    }

}