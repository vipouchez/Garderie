package models;


import java.time.LocalDate;

public class Entity {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String fatherName;
    protected Address address;
    protected LocalDate birthday;
    protected String imageUrl;

    public Entity(){
    }

}

