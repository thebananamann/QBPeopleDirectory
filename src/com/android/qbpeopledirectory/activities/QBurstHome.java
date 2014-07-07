package com.android.qbpeopledirectory.activities;

import android.app.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.qbpeopledirectory.R;
import com.parse.Parse;
import com.parse.ParseUser;


public class QBurstHome extends Activity {
    Fragment fragment;
    Spinner spinner,spinnernext,spinnerskill;

    public String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

        //Parse Initilization
        Parse.initialize(this, "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");
        ParseUser checkUser=ParseUser.getCurrentUser();

//Checks for user logged in and checks if user has verified the email by clicking the ling that was send during Sign Up process*/
        if(checkUser!=null&&checkUser.get("emailVerified").equals(true)){


            mPlanetTitles = new String[]{"Edit Profile","Search","QBurst Locations","About QBurst","Logout"};

            CurrentUserFragment newFragment=new CurrentUserFragment();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();




        }
        else {

            mPlanetTitles = new String[]{"Sign Up/Log In"};
            LogInFragment newFragment=new LogInFragment();
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();


        }




        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("Options");
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


    }
    //Assign spinner values for Location, Skills, Designation
    public void spinnerAssign(){
        spinner = (Spinner) findViewById(R.id.locationSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinnernext=(Spinner) findViewById(R.id.designationSP);
        ArrayAdapter<CharSequence> adapternext =ArrayAdapter.createFromResource(this, R.array.designation, android.R.layout.simple_spinner_dropdown_item);
        adapternext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernext.setAdapter(adapternext);

        spinnerskill=(Spinner) findViewById(R.id.skillsSP);
        ArrayAdapter<CharSequence> adapterskill =ArrayAdapter.createFromResource(this, R.array.skills, android.R.layout.simple_spinner_dropdown_item);
        adapternext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerskill.setAdapter(adapterskill);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.main, menu);*/
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        /*Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();*/
        Fragment newFragment = null;

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        ParseUser checkUserDrawer=ParseUser.getCurrentUser();
        if(checkUserDrawer!=null){
            if(position==0){

                newFragment=new EditProfileFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

            }
            else if(position==1){


                newFragment=new SearchForUser();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

            }
            else if(position==2){


                newFragment=new QBurstLocations();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

            }
            else if(position==3){
                final ProgressDialog dlg = new ProgressDialog(
                        this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Loading.  Please wait.");
                dlg.show();
                newFragment=new AboutQburst();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                dlg.dismiss();
                transaction.commit();

            }
            else if(position==4){
 /* ParseUser current=ParseUser.getCurrentUser();*/

                checkUserDrawer.logOut();

                Toast.makeText(this, "Successfully logged out.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,QBurstHome.class);
                startActivity(intent);
                finish();

            }



        }else{
            /*If user not logged in the Log In fragment is called*/
            if(position==0){
                newFragment=new LogInFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();

            }
        }





        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    //On back button is pressed it checks if the user is Logged in.
    //If user not Logged In then the Log In fragment is called.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(ParseUser.getCurrentUser()==null){
            Intent intent=new Intent(this,QBurstHome.class);
            startActivity(intent);
            finish();
        }
    }
}