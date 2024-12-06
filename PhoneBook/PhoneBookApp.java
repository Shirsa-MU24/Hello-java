import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {
    public static void main(String[] args) {
        TelephoneBook phoneBook = new TelephoneBook();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Telephone Book App!");
        boolean isAdmin = false;

        while (true) {
            System.out.println("\nOptions: 1. Create 2. Read 3. Update 4. Delete 5. Login as Admin 6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1 -> { // Create
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter city:");
                    String city = scanner.nextLine();
                    System.out.println("Enter postal code:");
                    String postalCode = scanner.nextLine();
                    System.out.println("Enter street name:");
                    String streetName = scanner.nextLine();
                    System.out.println("Enter gate number:");
                    int gateNumber = scanner.nextInt();
                    scanner.nextLine();

                    Address address = new Address(city, postalCode, streetName, gateNumber);
                    Profile profile = new Profile(firstName, lastName, age, address);

                    System.out.println("Enter number of phone numbers to add:");
                    int phoneCount = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < phoneCount; i++) {
                        System.out.println("Enter phone number:");
                        String phoneNumber = scanner.nextLine();
                        profile.addPhoneNumber(phoneNumber);
                    }

                    phoneBook.createProfile(profile);
                    System.out.println("Profile created successfully.");
                }
                case 2 -> { // Read
                    System.out.println("Search by: 1. Last Name 2. First Name 3. Street Name");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (searchChoice) {
                        case 1 -> {
                            System.out.println("Enter last name:");
                            String lastName = scanner.nextLine();
                            Profile result = phoneBook.searchByLastName(lastName);
                            System.out.println(result != null ? result : "No profile found.");
                        }
                        case 2 -> {
                            System.out.println("Enter first name:");
                            String firstName = scanner.nextLine();
                            List<Profile> results = phoneBook.searchByFirstName(firstName);
                            if (results.isEmpty()) System.out.println("No profiles found.");
                            else results.forEach(System.out::println);
                        }
                        case 3 -> {
                            System.out.println("Enter street name:");
                            String streetName = scanner.nextLine();
                            List<Profile> results = phoneBook.searchByStreetName(streetName);
                            if (results.isEmpty()) System.out.println("No profiles found.");
                            else results.forEach(System.out::println);
                        }
                    }
                }
                case 3 -> { // Update
                    if (!isAdmin) {
                        System.out.println("Admin access required to update profiles.");
                        break;
                    }

                    System.out.println("Enter last name of profile to update:");
                    String lastName = scanner.nextLine();
                    Profile profile = phoneBook.searchByLastName(lastName);

                    if (profile != null) {
                        System.out.println("Enter new city:");
                        String city = scanner.nextLine();
                        System.out.println("Enter new postal code:");
                        String postalCode = scanner.nextLine();
                        System.out.println("Enter new street name:");
                        String streetName = scanner.nextLine();
                        System.out.println("Enter new gate number:");
                        int gateNumber = scanner.nextInt();
                        scanner.nextLine();

                        Address newAddress = new Address(city, postalCode, streetName, gateNumber);

                        System.out.println("Enter number of new phone numbers:");
                        int phoneCount = scanner.nextInt();
                        scanner.nextLine();
                        List<String> newPhoneNumbers = new ArrayList<>();
                        for (int i = 0; i < phoneCount; i++) {
                            System.out.println("Enter phone number:");
                            String phoneNumber = scanner.nextLine();
                            newPhoneNumbers.add(phoneNumber);
                        }

                        phoneBook.updateProfile(profile, newAddress, newPhoneNumbers);
                        System.out.println("Profile updated successfully.");
                    } else {
                        System.out.println("No profile found.");
                    }
                }
                case 4 -> { // Delete
                    if (!isAdmin) {
                        System.out.println("Admin access required to delete profiles.");
                        break;
                    }

                    System.out.println("Enter last name of profile to delete:");
                    String lastName = scanner.nextLine();
                    Profile profile = phoneBook.searchByLastName(lastName);

                    if (profile != null) {
                        phoneBook.deleteProfile(profile);
                        System.out.println("Profile deleted successfully.");
                    } else {
                        System.out.println("No profile found.");
                    }
                }
                case 5 -> { // Login as Admin
                    System.out.println("Enter admin password:");
                    String password = scanner.nextLine();
                    if ("admin123".equals(password)) {
                        isAdmin = true;
                        System.out.println("Logged in as Admin.");
                    } else {
                        System.out.println("Incorrect password.");
                    }
                }
                case 6 -> { // Exit
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}