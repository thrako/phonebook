package phonebook.repository;

import org.springframework.stereotype.Repository;
import phonebook.entity.Contact;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class ContactRepository {
    private final Map<String, Contact> contacts = new TreeMap<>();

    public ContactRepository() {
        this.add(new Contact("Christian", "+359 887 333 333"));
        this.add(new Contact("Betty", "+359 889 222 222"));
        this.add(new Contact("Alexander", "+359 898 111 111"));
    }

    public void add(Contact contact) {
        if (contact.getName().isEmpty() || contact.getNumber().isEmpty()) {
            return;
        }
        this.contacts.put(contact.getName(), contact);
    }

    public void update(Contact contact) {
        this.contacts.values().stream()
                .filter(c -> c.getName().equals(contact.getName()))
                .findFirst()
                .ifPresent(c -> c.setNumber(contact.getNumber()));
    }

    public void delete(String name) {
        this.contacts.values().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .ifPresent(present -> this.contacts.remove(name));
    }

    public ArrayList<Contact> getAllAsList() {
        return new ArrayList<>(this.contacts.values());
    }

    public AbstractList<String> getNamesAsList() {
        return new ArrayList<>(this.contacts.keySet());
    }
}
