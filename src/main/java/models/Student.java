package models;


public class Student extends Entity{

    private String motherName;
    private String grandFatherName;
    private String fatherCin;
    private String fatherPhoneNumber;
    


    public Student(){
        super();
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public void setFatherCin(String fatherCin) {
        this.fatherCin = fatherCin;
    }

    public void setFatherPhoneNumber(String fatherPhoneNumber) {
        this.fatherPhoneNumber = fatherPhoneNumber;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public String getFatherCin() {
        return fatherCin;
    }

    public String getFatherPhoneNumber() {
        return fatherPhoneNumber;
    }


    @Override
    public String toString() {
        return "Student{" +

                ", motherName='" + motherName + '\'' +
                ", grandFatherName='" + grandFatherName + '\'' +
                ", fatherCin='" + fatherCin + '\'' +
                ", fatherPhoneNumber='" + fatherPhoneNumber + '\'' +
                '}';
    }

}


