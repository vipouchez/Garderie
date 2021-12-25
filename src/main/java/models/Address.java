package models;

public class Address  {

    private int id;
    private int postalCode;
    private String roadNumber;
    private String roadName;
    private String city;

    public String getRoadNumber() {
        return roadNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setRoadNumber(String roadNumber) {
        this.roadNumber = roadNumber;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", roadNumber='" + roadNumber + '\'' +
                ", roadName='" + roadName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}


