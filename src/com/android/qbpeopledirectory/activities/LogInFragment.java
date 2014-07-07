package com.android.qbpeopledirectory.activities;

import android.app.*;
import com.android.qbpeopledirectory.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.support.v4.app.ActionBarDrawerToggle;
import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LogInFragment extends Fragment{
    EditText usernameET;
    EditText passwordET;
    ListView L;

    /* (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        //Sets the ActionBar Title
        getActivity().getActionBar().setTitle("Log In");


        View view=inflater.inflate(R.layout.log_in_fragment, container, false);
        usernameET=(EditText) view.findViewById(R.id.userNameET);
        passwordET=(EditText) view.findViewById(R.id.passwordET);

        L=(ListView) view.findViewById(R.id.left_drawer);

//On click Listener for Register TextView
        TextView registerBT=(TextView) view.findViewById(R.id.registerHereTV);
        registerBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                CreateAccountFragment newFragment=new CreateAccountFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

            }
        });
//On click Listener for SignIn button
        Button signInBT=(Button) view.findViewById(R.id.signInBT);
        signInBT.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                final ProgressDialog dlg = new ProgressDialog(
                        getActivity());
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing in.  Please wait.");
                dlg.show();
                // Store values at the time of the login attempt.
                String username=(String) usernameET.getText().toString();
                String password=(String) passwordET.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback(){

                    @Override
                    public void done(ParseUser user, ParseException e) {
                        // Checks the username and password for Log In

                        if(user!=null&&ParseUser.getCurrentUser().get("emailVerified").equals(true)){



                            Toast.makeText(getActivity().getBaseContext(), "Log In Successfull", Toast.LENGTH_SHORT).show();

                            Intent myIntent = new Intent(getActivity(), QBurstHome.class);
                            getActivity().startActivity(myIntent);

                            CurrentUserFragment newFragment=new CurrentUserFragment();
                            FragmentTransaction transaction=getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, newFragment);
                            transaction.addToBackStack(null);
                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            transaction.commit();

                        }
                        else {


                            Toast.makeText(getActivity().getBaseContext(), "Log In Unsuccessfull", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity().getBaseContext(), "Try Again", Toast.LENGTH_SHORT).show();

                            usernameET.setText("");
                            passwordET.setText("");
                            dlg.dismiss();
                        }
                    }

                });
            }
        });

        return view;
    }

}