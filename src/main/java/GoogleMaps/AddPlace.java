package GoogleMaps;

import java.util.ArrayList;
import java.util.List;

public class AddPlace {

    private static int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String language;
    private Location location;
    private List<String> types;

    public static int getAccuracy() {
        return accuracy;
    }

    public static void setAccuracy(int accuracy) {
        AddPlace.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }


    public static AddPlace getAddPlace() {
        AddPlace p = new AddPlace();

        setAccuracy(50);

        p.setAddress("29, side layout, cohen 09");

        p.setLanguage("French");

        p.setName("Frontline house");

        p.setPhone_number("(+91) 983 893 3937");

        p.setWebsite("http://google.com");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setIng(33.427362);
        p.setLocation(location);
        return p;
    }



}
