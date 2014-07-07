package com.android.qbpeopledirectory.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.*;
import com.android.qbpeopledirectory.R;
import com.parse.*;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;

public class EditProfileFragment extends Fragment{


    ParseUser currentUser=ParseUser.getCurrentUser();
    EditText firstName,lastName,designation,phoneNo,dob,password,skills,Address,location;
    String first,last,desig,phone,doB,passWord,skillS,addresS,locatioN;
    ImageView editUserPic;
    ParseFile imageFile;

    TextView choosePic,emailTitleTV;
    ImageView imageUser;
    private static final int SELECT_PICTURE=1;
    private String selectedImagePath;
    private Button doneBT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Finding IDs for  views
        View view=inflater.inflate(R.layout.edit_user_profile, container, false);
        firstName=(EditText) view.findViewById(R.id.firstName);
        lastName=(EditText) view.findViewById(R.id.lastName);
        designation=(EditText) view.findViewById(R.id.designation);
        phoneNo=(EditText) view.findViewById(R.id.phoneNo);
        dob=(EditText) view.findViewById(R.id.dob);
        Address=(EditText) view.findViewById(R.id.address);
        location=(EditText) view.findViewById(R.id.location);






        skills=(EditText) view.findViewById(R.id.skills);
        editUserPic=(ImageView) view.findViewById(R.id.editUserPic);
        choosePic=(TextView) view.findViewById(R.id.changePicTV);
        emailTitleTV=(TextView) view.findViewById(R.id.emailTitleTV);
        doneBT=(Button) view.findViewById(R.id.doneBT);

        first=currentUser.get("firstName").toString();
        last=currentUser.get("lastName").toString();
        desig=currentUser.get("designation").toString();
        phone=currentUser.get("phone").toString();
        doB=currentUser.get("dateOfBirth").toString();

        skillS=currentUser.get("skill").toString();
        addresS=currentUser.get("address").toString();
        locatioN=currentUser.get("location").toString();
        imageFile=(ParseFile) currentUser.get("image");

        emailTitleTV.setText(currentUser.getEmail());

        /*On click Listener for DONE button*/
        doneBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser=ParseUser.getCurrentUser();



                //Checks for change in fields

                if(!firstName.getText().toString().equals("")){

                    currentUser.put("firstName",firstName.getText().toString());
                }
                if(!lastName.getText().toString().equals("")){

                    currentUser.put("lastName",lastName.getText().toString());
                }
                if(!phoneNo.getText().toString().equals("")){

                    currentUser.put("phone",phoneNo.getText().toString());
                }
                if(!location.getText().toString().equals("")){

                    currentUser.put("location",location.getText().toString());
                }
                if(!dob.getText().toString().equals("")){

                    currentUser.put("dateOfBirth",dob.getText().toString());
                }
                if(!designation.getText().toString().equals("")){

                    currentUser.put("designation",firstName.getText().toString());
                }
                if(!Address.getText().toString().equals("")){

                    currentUser.put("address",Address.getText().toString());
                }
                if(!skills.getText().toString().equals("")){

                    currentUser.put("skill",skills.getText().toString());
                }




                Bitmap bitmap = ((BitmapDrawable)editUserPic.getDrawable()).getBitmap();
                // Convert it to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
                byte[] image = stream.toByteArray();

                final ParseFile file = new ParseFile("image.png", image);

                file.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        ParseUser.getCurrentUser().put("image",file);
                        
                        ParseUser.getCurrentUser().saveInBackground();
                    }
                });






                currentUser.saveInBackground();

                Toast.makeText(getActivity().getBaseContext(),"Fields are saved successfully",Toast.LENGTH_SHORT).show();
/*Go back to Current user page*/
                CurrentUserFragment newFragment=new CurrentUserFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();




            }






        });

/*Setting the profile picture from Parse.com*/
        imageFile.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {

                if(e==null){


                    editUserPic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                }

            }
        });
/*Setting hint for already assigned values from Parse.com*/
        firstName.setHint(first);
        lastName.setHint(last);
        designation.setHint(desig);
        phoneNo.setHint(phone);
        dob.setHint(doB);
        skills.setHint(skillS);
        location.setHint(locatioN);
        Address.setHint(addresS);

/*On click Listener for TextView, which chooses image from Gallery*/
        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });



        return view;
    }
    /*Choose an image from Gallery*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                /*imageUser=(ImageView) getActivity().findViewById(R.id.editUserPic);*/
                editUserPic.setImageURI(selectedImageUri);







            }
        }
    }
    /* Get image path from Gallery*/
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



}