package models;


import java.time.LocalDate;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Address address;
    private LocalDate birthday;
    private String imageUrl;

    private String motherName;
    private String grandFatherName;
    private String fatherCin;
    private String fatherPhoneNumber;


    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public String getFatherCin() {
        return fatherCin;
    }

    public void setFatherCin(String fatherCin) {
        this.fatherCin = fatherCin;
    }

    public String getFatherPhoneNumber() {
        return fatherPhoneNumber;
    }

    public void setFatherPhoneNumber(String fatherPhoneNumber) {
        this.fatherPhoneNumber = fatherPhoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", address=" + address +
                ", birthday=" + birthday +
                ", imageUrl='" + imageUrl + '\'' +
                ", motherName='" + motherName + '\'' +
                ", grandFatherName='" + grandFatherName + '\'' +
                ", fatherCin='" + fatherCin + '\'' +
                ", fatherPhoneNumber='" + fatherPhoneNumber + '\'' +
                '}';
    }
}


