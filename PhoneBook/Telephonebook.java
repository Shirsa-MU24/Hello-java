import java.util.ArrayList; // Import ArrayList to manage dynamic lists.
import java.util.List;      // Import List interface for defining lists.

class TelephoneBook {
    private List<Profile> profiles; // List to store all Profile objects.

    // Constructor: Initializes the profiles list as an empty ArrayList.
    public TelephoneBook() {
        profiles = new ArrayList<>();
    }

    // Method to add a new profile to the list.
    public void createProfile(Profile profile) {
        profiles.add(profile); // Adds the provided Profile object to the profiles list.
    }

    // Method to search for a profile by last name.
    public Profile searchByLastName(String lastName) {
        for (Profile profile : profiles) { // Iterates through each Profile in the list.
            if (profile.getLastName().equalsIgnoreCase(lastName)) {
                // Compares the last name (case-insensitive).
                return profile; // Returns the first matching Profile.
            }
        }
        return null; // Returns null if no profile matches the last name.
    }

    // Method to search for profiles by first name.
    public List<Profile> searchByFirstName(String firstName) {
        List<Profile> results = new ArrayList<>(); // List to store matching profiles.
        for (Profile profile : profiles) { // Iterates through each Profile.
            if (profile.getFirstName().equalsIgnoreCase(firstName)) {
                // Compares the first name (case-insensitive).
                results.add(profile); // Adds matching Profile to the results list.
            }
        }
        return results; // Returns the list of matches.
    }

    // Method to search for profiles by street name.
    public List<Profile> searchByStreetName(String streetName) {
        List<Profile> results = new ArrayList<>(); // List to store matching profiles.
        for (Profile profile : profiles) { // Iterates through each Profile.
            if (profile.getAddress().getStreetName().equalsIgnoreCase(streetName)) {
                // Compares the street name (case-insensitive).
                results.add(profile); // Adds matching Profile to the results list.
            }
        }
        return results; // Returns the list of matches.
    }

    // Method to update an existing profile's address and phone numbers.
    public void updateProfile(Profile profile, Address newAddress, List<String> newPhoneNumbers) {
        profile.updateContactInfo(newAddress, newPhoneNumbers);
        // Calls the Profile's method to update its contact information.
    }

    // Method to delete a profile from the list.
    public void deleteProfile(Profile profile) {
        profiles.remove(profile); // Removes the specified Profile from the list.
    }

    // Method to display all profiles in the telephone book.
    public void displayAllProfiles() {
        for (Profile profile : profiles) { // Iterates through each Profile in the list.
            System.out.println(profile); // Prints the Profile's details.
        }
    }
}
