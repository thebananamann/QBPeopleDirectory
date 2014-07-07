package com.android.qbpeopledirectory.activities;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.BitmapDrawable;
import android.widget.*;
import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.View.OnClickListener;

public class CreateAccountFragment extends DialogFragment implements View.OnClickListener{

    ;
    /* (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterskill,adapternext;
    Spinner spinner,spinnernext,spinnerskill;
    EditText firstname,address,phonenumber;
    EditText lastname,email,dob,password,username;
    RadioButton maleRB,femaleRB;
    EditText dobET;
    EditText phone;

    EditText yourEditText;
    private static final int SELECT_PICTURE=1;
    private String selectedImagePath;
    private ImageView imageUser;
    ParseFile file;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Sets title on ActionBar
        getActivity().getActionBar().setTitle("Sign Up");

        // TODO Auto-generated method stub
        final View view = inflater.inflate(R.layout.sign_up_fragment,
                container, false);

        //Parse initilization
        Parse.initialize(getActivity().getBaseContext(), "88m6iI4WyC4WbeKLqwjHlUdqjbWo1tNGDNlK6acR", "JNxSm9eqhDjFO87rTSs6ax0ClFEvH3svZFugc6xT");

        //Date picker for Date of Birth
        yourEditText=(EditText) view.findViewById(R.id.dobET);
        yourEditText.setOnClickListener(this);


        //Assign Spinner Values
        spinnerAssign(view);

        //Uploads the picture
        TextView uploadTV=(TextView) view.findViewById(R.id.textView);
        uploadTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });






        // On click Save button
        Button buttonSave= (Button) view.findViewById(R.id.signupBT);
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                phone=(EditText) view.findViewById(R.id.phoneET);
                int length1=phone.getText().length();


                firstname=(EditText) view.findViewById(R.id.firstNameET);
                lastname=(EditText) view.findViewById(R.id.lastNameET);
                email=(EditText) view.findViewById(R.id.emailET);
                phonenumber=(EditText) view.findViewById(R.id.phoneET);
                dob=(EditText) view.findViewById(R.id.dobET);
                address=(EditText) view.findViewById(R.id.addressET);
                password=(EditText) view.findViewById(R.id.passwordET);
                username=(EditText) view.findViewById(R.id.usernameET);
                maleRB=(RadioButton) view.findViewById(R.id.maleRadio);
                int length2=firstname.getText().toString().length();
                int length3=lastname.getText().toString().length();
                int length4=email.getText().toString().length();
                int length5=password.getText().toString().length();
                int length6=address.getText().toString().length();
                int length7=username.getText().toString().length();

                if(length2==0)
                {
                    Toast.makeText(getActivity().getBaseContext(), "First Name missing", Toast.LENGTH_SHORT).show();

                }
                else if(length3==0){
                    Toast.makeText(getActivity().getBaseContext(), "Last Name missing.", Toast.LENGTH_SHORT).show();
                }
                else if (length4==0){
                    Toast.makeText(getActivity().getBaseContext(), "Email missing.", Toast.LENGTH_SHORT).show();
                }
                else if(length1!=10){
                    Toast.makeText(getActivity().getBaseContext(), "Phone Number must be 10 digits.", Toast.LENGTH_SHORT).show();
                }

                else if(length6==0){
                    Toast.makeText(getActivity().getBaseContext(), "Address missing.", Toast.LENGTH_SHORT).show();

                }

                else if(length5<6)
                {
                    Toast.makeText(getActivity().getBaseContext(), "Password atleast 6 characters.", Toast.LENGTH_SHORT).show();
                }else if(length7<6){
                    Toast.makeText(getActivity().getBaseContext(), "Username atleast 6 characters.", Toast.LENGTH_SHORT).show();
                }
                else{

                    ParseQuery<ParseUser> queryNew = ParseUser.getQuery();
                    queryNew.whereEqualTo("email", email.getText().toString());
                    String emails= null;
                    try {
                        emails = queryNew.getFirst().getEmail().toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(email.getText().toString().equals(emails)){
                        Toast.makeText(getActivity().getBaseContext(), "Email already taken", Toast.LENGTH_SHORT).show();
                        email.setText("");
                    }


                    ParseQuery<ParseUser> query = ParseUser.getQuery();
                    query.whereEqualTo("username", username.getText().toString());

                    try {
                        String users= query.getFirst().getUsername().toString();

                        if(users.equals(username.getText().toString())){
                            Toast.makeText(getActivity().getBaseContext(), "User already Exists", Toast.LENGTH_SHORT).show();
                            username.setText("");
                        }

                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }finally{


                        if(!username.getText().toString().equals("")){

                            ParseUser user = new ParseUser();
                            user.setUsername(username.getText().toString());
                            user.setPassword(password.getText().toString());
                            user.setEmail(email.getText().toString());


                            user.put("firstName",firstname.getText().toString());

                            user.put("lastName", lastname.getText().toString());

                            user.put("designation", spinnernext.getSelectedItem().toString());
                            user.put("location", spinner.getSelectedItem().toString());
                            user.put("skill", spinnerskill.getSelectedItem().toString());
                            user.put("dateOfBirth", dob.getText().toString());

                            if(maleRB.isChecked()){
                                user.put("gender", true);
                            }
                            else{
                                user.put("gender", false);
                            }
                            user.put("address", address.getText().toString());
                            user.put("phone", phone.getText().toString());

                            imageUser=(ImageView) getActivity().findViewById(R.id.uploadPicIV);
                            Bitmap bitmap = ((BitmapDrawable)imageUser.getDrawable()).getBitmap();
                            // Convert it to byte
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            // Compress image to lower quality scale 1 - 100
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] image = stream.toByteArray();

                            file = new ParseFile("image.png", image);

                            file.saveInBackground();

// Sign Up with given fields

                            user.signUpInBackground(new SignUpCallback() {

                                @Override
                                public void done(ParseException e) {
                                    // TODO Auto-generated method stub
                                    if (e == null) {
                                        Toast.makeText(getActivity().getBaseContext(), "Successfully signed up.", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity().getBaseContext(), "Please verify your Email. .", Toast.LENGTH_SHORT).show();
                                        ParseUser signUpUser = ParseUser.getCurrentUser();
                                        signUpUser.put("image",file);
                                        signUpUser.saveInBackground();
                                        ParseUser.logOut();
                                        Intent intent=new Intent(getActivity().getBaseContext(),QBurstHome.class);
                                        startActivity(intent);


                                    } else {
                                        e.printStackTrace();
                                        Toast.makeText(getActivity().getBaseContext(), "Error in Signing Up." + username.getText().toString(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity().getBaseContext(), "Try Sign up again. ", Toast.LENGTH_SHORT).show();

                                    }
                                }

                            });
                        }
                    }


                }


            }
        });


        //Radio button for Male/Female

        RadioButton male=(RadioButton) view.findViewById(R.id.maleRadio);

        male.setChecked(true);
        RadioButton fem=(RadioButton) view.findViewById(R.id.femaleRadio);
        fem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                RadioButton female=(RadioButton) view.findViewById(R.id.femaleRadio);
                RadioButton mle=(RadioButton) view.findViewById(R.id.maleRadio);
                female.setChecked(true);
                mle.setChecked(false);

            }
        });
        male.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                RadioButton female=(RadioButton) view.findViewById(R.id.femaleRadio);
                RadioButton mle=(RadioButton) view.findViewById(R.id.maleRadio);
                female.setChecked(false);
                mle.setChecked(true);
            }
        });
        return view;
    }






//Method to assign spinner values

    public void spinnerAssign(View view){
        spinner = (Spinner) view.findViewById(R.id.locationSP);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinnernext=(Spinner) view.findViewById(R.id.designationSP);
        adapternext =ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.designation, android.R.layout.simple_spinner_dropdown_item);
        adapternext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnernext.setAdapter(adapternext);

        spinnerskill=(Spinner) view.findViewById(R.id.skillsSP);
        adapterskill =ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.skills, android.R.layout.simple_spinner_dropdown_item);
        adapternext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerskill.setAdapter(adapterskill);
    }

    //Method to show DatePicker

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    OnDateSetListener ondate = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            yourEditText.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));

        }
    };

    //Method to select image for user picture from Gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                imageUser=(ImageView) getActivity().findViewById(R.id.uploadPicIV);
                imageUser.setImageURI(selectedImageUri);

                Bitmap bitmap = ((BitmapDrawable)imageUser.getDrawable()).getBitmap();
                // Convert it to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();

                file = new ParseFile("image.png", image);

                file.saveInBackground();




            }
        }
    }
    // Method to get the path of image from Gallery
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    //Onclick implementation

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.dobET:
                showDatePicker();
                break;

        }
    }


}
	