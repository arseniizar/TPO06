package com.example.tpo06.dto;

public class PersonDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String university;
    private String country;
    private String arbitrary1;
    private String arbitrary2;
    private String arbitrary3;
    private String arbitrary4;
    private String arbitrary5;

    public PersonDTO() {
    }

    public PersonDTO(String firstName,
                     String lastName,
                     String dateOfBirth,
                     String address,
                     String university,
                     String country,
                     String arbitrary1, String arbitrary2, String arbitrary3, String arbitrary4, String arbitrary5) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.university = university;
        this.country = country;
        this.arbitrary1 = arbitrary1;
        this.arbitrary2 = arbitrary2;
        this.arbitrary3 = arbitrary3;
        this.arbitrary4 = arbitrary4;
        this.arbitrary5 = arbitrary5;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArbitrary1() {
        return arbitrary1;
    }

    public void setArbitrary1(String arbitrary1) {
        this.arbitrary1 = arbitrary1;
    }

    public String getArbitrary2() {
        return arbitrary2;
    }

    public void setArbitrary2(String arbitrary2) {
        this.arbitrary2 = arbitrary2;
    }

    public String getArbitrary3() {
        return arbitrary3;
    }

    public void setArbitrary3(String arbitrary3) {
        this.arbitrary3 = arbitrary3;
    }

    public String getArbitrary4() {
        return arbitrary4;
    }

    public void setArbitrary4(String arbitrary4) {
        this.arbitrary4 = arbitrary4;
    }

    public String getArbitrary5() {
        return arbitrary5;
    }

    public void setArbitrary5(String arbitrary5) {
        this.arbitrary5 = arbitrary5;
    }
}
