package com.example.android.sample1;

public class Member {
    private String Name;
    private String FathersName;
    private String MothersName;
    private String Dob;
    private String Gender;
    private String ContactNumber;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFathersName() {
        return FathersName;
    }

    public void setFathersName(String fathersName) {
        FathersName = fathersName;
    }

    public String getMothersName() {
        return MothersName;
    }

    public void setMothersName(String mothersName) {
        MothersName = mothersName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
