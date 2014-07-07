package com.android.qbpeopledirectory.activities;

import com.parse.ParseFile;

public class RowItem {

    private String FirstName;
    private ParseFile profile_pic_id;
    private String LastName;
    private String Designation;

    public RowItem(String FirstName, ParseFile profile_pic_id, String LastName,
                   String Designation) {

        this.FirstName = FirstName;
        this.profile_pic_id = profile_pic_id;
        this.Designation = Designation;
        this.LastName = LastName;
    }
    //Methods to get the required fields.
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public ParseFile getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(ParseFile profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setStatus(String Designation) {
        this.Designation = Designation;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

}