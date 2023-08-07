

public class Bike {
    private String bikeBrand;
    private String bikeModel;
    private String bikeDescription;

    public Bike(String bikeBrand, String bikeModel, String bikeDescription) {
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeDescription = bikeDescription;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getBikeDescription() {
        return bikeDescription;
    }

    public void display() {
        System.out.println("Bike Brand: " + bikeBrand);
        System.out.println("Bike Model: " + bikeModel);
        System.out.println("Bike Description: " + bikeDescription);
    }
}
