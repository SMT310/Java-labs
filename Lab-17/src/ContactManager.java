import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        this.contacts = new ArrayList<>();
        contacts.add(new Contact("Alice Smith", "0912345678", "alice@example.com", "123 Main St"));
        contacts.add(new Contact("Bob Johnson", "0987654321", "bob@example.com", "456 Oak Ave"));
        contacts.add(new Contact("Charlie Brown", "0900112233", "charlie@example.com", "789 Pine Ln"));
    }

    public void addContact(String fullName, String phoneNumber, String email, String address) {
        // Check if a contact with the same phone number already exists using Stream API
        boolean contactExists = contacts.stream()
                .anyMatch(c -> c.getPhoneNumber().equals(phoneNumber));

        if (contactExists) {
            System.out.println("Error: Contact with phone number " + phoneNumber + " already exists.");
        } else {
            contacts.add(new Contact(fullName, phoneNumber, email, address));
            System.out.println("Contact added successfully!");
        }
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }
        System.out.println("\n--- All Contacts ---");
        contacts.forEach(System.out::println); 
        System.out.println("--------------------");
    }

    public Optional<Contact> findContactByPhoneNumber(String phoneNumber) {
        return contacts.stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .findFirst();
    }

    public void updateContact(String oldPhoneNumber, String newFullName, String newPhoneNumber, String newEmail,
            String newAddress) {
        Optional<Contact> contactOptional = findContactByPhoneNumber(oldPhoneNumber);

        contactOptional.ifPresentOrElse(
                contact -> {
                    if (!oldPhoneNumber.equals(newPhoneNumber)
                            && contacts.stream().anyMatch(c -> c.getPhoneNumber().equals(newPhoneNumber))) {
                        System.out.println(
                                "Error: New phone number " + newPhoneNumber + " already exists for another contact.");
                        return;
                    }
                    contact.setFullName(newFullName);
                    contact.setEmail(newEmail);
                    contact.setAddress(newAddress);
                    System.out.println("Contact updated successfully!");
                },
                () -> System.out.println("Error: Cannot find contact with phone number " + oldPhoneNumber));
    }

    public void deleteContact(String phoneNumber) {
        boolean removed = contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
        if (removed) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Error: Cannot find contact with phone number " + phoneNumber);
        }
    }
}
