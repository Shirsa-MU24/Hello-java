import java.util.ArrayList; // Importing ArrayList class for dynamic list management.
import java.util.List;      // Importing List interface to define a list of items.

class Profile {
    // Defining the Profile class which represents a person in the telephone book.

    private String firstName;    // First name of the profile.
    private String lastName;     // Last name of the profile.
    private int age;             // Age of the profile.
    private Address address;     // Address object to store the profile's address.
    private List<String> phoneNumbers; // List of phone numbers associated with the profile.

    // Constructor to initialize the Profile object with basic details.
    public Profile(String firstName, String lastName, int age, Address address) {
        this.firstName = firstName;           // Assigning the provided first name to the object.
        this.lastName = lastName;             // Assigning the provided last name to the object.
        this.age = age;                       // Assigning the provided age to the object.
        this.address = address;               // Assigning the provided address to the object.
        this.phoneNumbers = new ArrayList<>();// Initializing the phoneNumbers list as empty.
    }

    // Method to add a new phone number to the profile's list of phone numbers.
    public void addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber); // Adds the given phone number to the phoneNumbers list.
    }

    // Getter method to retrieve the last name of the profile.
    public String getLastName() {
        return lastName; // Returns the profile's last name.
    }

    // Getter method to retrieve the first name of the profile.
    public String getFirstName() {
        return firstName; // Returns the profile's first name.
    }

    // Getter method to retrieve the address of the profile.
    public Address getAddress() {
        return address; // Returns the profile's address.
    }

    // Method to update the profile's contact information.
    public void updateContactInfo(Address newAddress, List<String> newPhoneNumbers) {
        this.address = newAddress;          // Updates the profile's address.
        this.phoneNumbers = newPhoneNumbers;// Replaces the current phoneNumbers list with the new list.
    }

    // Overriding the toString() method to provide a string representation of the Profile.
    @Override
    public String toString() {
        // Returns a detailed string with the profile's name, age, address, and phone numbers.
        return "Name: " + firstName + " " + lastName +
                ", Age: " + age +
                ", Address: " + address +
                ", Phone Numbers: " + phoneNumbers;
    }
}
