import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    private int idCounter;

    public ContactManager() {
        this.contacts = new ArrayList<>();
        this.idCounter = 1;
    }

    public void addContact(String name, String phone, String email, String address) {
            String id = String.format("C%03d", idCounter++);
            Contact contact = new Contact(id, name, phone, email, address);
            contacts.add(contact);
            System.out.println("Add contact successfully!");
    }

    public Contact findContactByPhoneNumber(String phone) {
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }

    public void updateContact(String name, String phone, String email, String address) {
        Contact contact = findContactByPhoneNumber(phone);
        if (contact != null) {
            contact.setName(name);
            contact.setPhone(phone);
            contact.setEmail(email);
            contact.setAddress(address);
            System.out.println("Edit contact successfully!");
        } else {
            System.out.println("Error: Cannot find contact with phone number " + phone);
        }
    }

    public void deleteContact(String phone) {
        Contact contact = findContactByPhoneNumber(phone);
        if (contact != null) {
            contacts.remove(contact);
            System.out.println("Delete contact successfully!");
        } else {
            System.out.println("Error: Cannot find contact with phone number " + phone);
        }
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact empty!");
        } else {
            System.out.println("\nContact list:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}