package models;

public class Address  {

    private String id;
    private String postalCode;
    private String roadNumber;
    private String roadName;
    private String city;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRoadNumber() {
        return roadNumber;
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



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
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


