class Address {
    private String city;
    private String postalCode;
    private String streetName;
    private int gateNumber;

    public Address(String city, String postalCode, String streetName, int gateNumber) {
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.gateNumber = gateNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    @Override
    public String toString() {
        return gateNumber + " " + streetName + ", " + city + " " + postalCode;
    }
}
