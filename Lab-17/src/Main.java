import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ContactManager contactManager = new ContactManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    contactManager.displayContacts();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    addContact();
                    break;
                case "4":
                    updateContact();
                    break;
                case "5":
                    deleteContact();
                    break;
                default:
                    System.out.println("\n\n=== THANKS FOR USING OUR SERVICES ===\n\n!");
                    scanner.close();
                    return;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== CONTACT MANAGEMENT =====\n\n");
        System.out.println("1. Show all contacts");
        System.out.println("2. Find contact by phone number");
        System.out.println("3. Add new contact");
        System.out.println("4. Edit contact information");
        System.out.println("5. Delete contact\n\n");
        System.out.print("Please choose 1->5 or other button to exit: ");
    }

    private static String getValidName() {
        while (true) {
            System.out.print("Enter fullName: ");
            String name = scanner.nextLine().trim();
            if (name.matches("[a-zA-Z\\s]+") && !name.isEmpty()) {
                return name;
            }
            System.out.println("Error: Name is only contained letters and spaces, not left blank!");
        }
    }

    private static String getValidPhone() {
        while (true) {
            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine().trim();
            if (phone.matches("\\d{10,11}")) {
                return phone;
            }
            System.out.println(" Error: Phone number must contain 10-11 digits!");
        }
    }

    private static String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            if (Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
                return email;
            }
            System.out.println(
                    " Error: invalid email (must contain @ and domain names, for example: Example@domain.com)!");
        }
    }

    private static String getValidAddress() {
        while (true) {
            System.out.print("Enter address: ");
            String address = scanner.nextLine().trim();
            if (!address.isEmpty()) {
                return address;
            }
            System.out.println("Error: Address must not be empty!");
        }
    }

    private static void searchContact() {
        String phoneNumber = getValidPhone();
        Optional<Contact> contactOptional = contactManager.findContactByPhoneNumber(phoneNumber); // Use Optional

        contactOptional.ifPresentOrElse(
                contact -> System.out.println("Found contact: " + contact),
                () -> System.out.println("Cannot find phone number " + phoneNumber));
    }

    private static void addContact() {
        String name = getValidName();
        String phone = getValidPhone();
        String email = getValidEmail();
        String address = getValidAddress();
        contactManager.addContact(name, phone, email, address);
    }

    private static void updateContact() {
        System.out.print("Enter phone number of contact to update: ");
        String oldPhone = scanner.nextLine();

        Optional<Contact> contactOptional = contactManager.findContactByPhoneNumber(oldPhone);

        contactOptional.ifPresentOrElse(
                contactToUpdate -> {
                    System.out.println("Contact found. Enter new details:");
                    String newName = getValidName();
                    String newEmail = getValidEmail();
                    String newAddress = getValidAddress();
                    String newPhone = getValidPhone();

                    contactManager.updateContact(oldPhone, newName, newPhone, newEmail, newAddress);
                },
                () -> System.out.println("Error: Cannot find contact with phone number " + oldPhone));
    }

    private static void deleteContact() {
        System.out.print("Enter phone number of contact to delete: ");
        String phone = scanner.nextLine();
        contactManager.deleteContact(phone);
    }
}
