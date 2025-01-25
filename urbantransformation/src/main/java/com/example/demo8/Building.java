package com.example.demo8;

public abstract class Building {
    private String id;
    private String address;
    private int age;
    private String green;
    private int floorHeight;
    private int wallThickness;
    private String wallType;
    private String floorType;
    private String roofType;
    private String earthquakeRisk;
    private String disasterRisk;
    private String compliance;
    private String budget;

    // Constructor
    public Building(String id, String address, int age, String green, int floorHeight, int wallThickness,
                    String wallType, String floorType, String roofType, String earthquakeRisk,
                    String disasterRisk, String compliance, String budget) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.green = green;
        this.floorHeight = floorHeight;
        this.wallThickness = wallThickness;
        this.wallType = wallType;
        this.floorType = floorType;
        this.roofType = roofType;
        this.earthquakeRisk = earthquakeRisk;
        this.disasterRisk = disasterRisk;
        this.compliance = compliance;
        this.budget = budget;
    }

    // Getter and setter methods
    public String getId() { return id; }
    public String getAddress() { return address; }
    public int getAge() { return age; }
    public String getGreen() { return green; }
    public int getFloorHeight() { return floorHeight; }
    public int getWallThickness() { return wallThickness; }
    public String getWallType() { return wallType; }
    public String getFloorType() { return floorType; }
    public String getRoofType() { return roofType; }
    public String getEarthquakeRisk() { return earthquakeRisk; }
    public String getDisasterRisk() { return disasterRisk; }
    public String getCompliance() { return compliance; }
    public String getBudget() { return budget; }

    public void setAddress(String address) { this.address = address; }
    public void setAge(int age) { this.age = age; }
    public void setGreen(String green) { this.green = green; }
    public void setFloorHeight(int floorHeight) { this.floorHeight = floorHeight; }
    public void setWallThickness(int wallThickness) { this.wallThickness = wallThickness; }
    public void setWallType(String wallType) { this.wallType = wallType; }
    public void setFloorType(String floorType) { this.floorType = floorType; }
    public void setRoofType(String roofType) { this.roofType = roofType; }
    public void setEarthquakeRisk(String earthquakeRisk) { this.earthquakeRisk = earthquakeRisk; }
    public void setDisasterRisk(String disasterRisk) { this.disasterRisk = disasterRisk; }
    public void setCompliance(String compliance) { this.compliance = compliance; }
    public void setBudget(String budget) { this.budget = budget; }

    // Abstract method to display building-specific information
    public abstract void displayInfo();
}

class ResidentialBuilding extends Building {

    // ResidentialBuilding Constructor
    public ResidentialBuilding(String id, String address, int age, String green, int floorHeight, int wallThickness,
                               String wallType, String floorType, String roofType, String earthquakeRisk,
                               String disasterRisk, String compliance, String budget) {
        super(id, address, age, green, floorHeight, wallThickness, wallType, floorType, roofType, earthquakeRisk, disasterRisk, compliance, budget);
    }

    @Override
    public void displayInfo() {
        // Displaying specific information for residential buildings
        System.out.println("Residential Building Basic Information\t\t\t ID: " + getId() + "\t\tADDRESS: " + getAddress());
    }
}

class CommercialBuilding extends Building {

    // CommercialBuilding Constructor
    public CommercialBuilding(String id, String address, int age, String green, int floorHeight, int wallThickness,
                              String wallType, String floorType, String roofType, String earthquakeRisk,
                              String disasterRisk, String compliance, String budget) {
        super(id, address, age, green, floorHeight, wallThickness, wallType, floorType, roofType, earthquakeRisk, disasterRisk, compliance, budget);
    }

    @Override
    public void displayInfo() {
        // Displaying specific information for commercial buildings
        System.out.println("Commercial Building Basic Information\t\t\t ID: " + getId() + "\t\tADDRESS: " + getAddress());
    }
}
