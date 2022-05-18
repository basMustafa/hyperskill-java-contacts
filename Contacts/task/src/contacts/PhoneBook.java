package contacts;

import application.ContactApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

    private final List<Contact> contactList = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public void addContact() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        if ("person".equals(type)) {
            addPerson();
        } else if ("organization".equals(type)) {
            addOrganization();
        }
        ContactApp.start();
    }

    public void addPerson() {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname:");
        String surName = scanner.nextLine();
        System.out.println("Enter the birth date:");
        String birthDate = scanner.nextLine();

        if (birthDate.isEmpty()) {
            System.out.println("Bad birth date!");
        }

        System.out.println("Enter the gender (M, F):");
        String genderContact = scanner.nextLine();
        Gender gender = null;

        if ("M".equals(genderContact)) {
            gender = Gender.MALE;
        } else if ("F".equals(genderContact)) {
            gender = Gender.FEMALE;
        } else {
            System.out.println("No such gender");
        }

        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        contactList.add(new Person.Builder().
                setNumber(number).
                setName(name).
                setSurName(surName).
                setBirthDate(birthDate).
                setGender(gender).
                build());
        System.out.println("The record added.");
    }

    public void addOrganization() {
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        contactList.add(new Organization.Builder().
                setNumber(number).
                setName(name).
                setAddress(address).
                build());
        System.out.println("The record added.");
    }

    public void removeContact(int index) {
        contactList.remove(index);
        System.out.println("Deleted");
    }

    public void editContact(int index) {
        contactList.get(index).selectField();
        String field = scanner.nextLine().toLowerCase();
        if (!contactList.get(index).checkField(field)) {
            System.out.println("No such field");
            editContact(index);
        } else {
            System.out.printf("Enter %s:%n", field);
            String value = scanner.nextLine();
            contactList.get(index).editField(field, value);
            System.out.println("Saved");
            getInfo(index);
            ContactApp.record(index);
        }
    }

    public void listContacts() {
        if (contactList.isEmpty()) {
            System.out.println("The Phone Book has 0 records");
            ContactApp.start();
        } else {
            for (int i = 0; i < contactList.size(); i++) {
                System.out.println((i + 1) + ". " + contactList.get(i).getFullName());
            }
            ContactApp.list();
        }
    }

    public void countContacts() {
        System.out.printf("The Phone Book has %d records%n", contactList.size());
        ContactApp.start();
    }

    public void getInfo(int index) {
        System.out.println(contactList.get(index));
    }

    public void search() {
        if (contactList.isEmpty()) {
            System.out.println("The Phone Book has 0 records");
            ContactApp.start();
        } else {
            System.out.println("Enter search query: ");
            String pattern = scanner.nextLine().toUpperCase();
            List<Integer> tempList = new ArrayList<>();

            for (int i = 0; i < contactList.size(); i++) {
                if (contactList.get(i).toString().toUpperCase().contains(pattern)) {
                    tempList.add(i);
                }
            }

            System.out.printf("Found %d results:%n", tempList.size());

            if (tempList.size() == 0) {
                ContactApp.start();
            }

            for (int i = 0; i < tempList.size(); i++) {
                System.out.printf("%d %s%n", i + 1, contactList.get(tempList.get(i)).getFullName());
            }

            System.out.println("\n[search] Enter action ([number], back, again):");
            String select = scanner.next();

            switch (select) {
                case "back":
                    ContactApp.start();
                case "again":
                    search();
                    break;
                default:
                    int index = Integer.parseInt(select) - 1;
                    System.out.println(contactList.get(tempList.get(index)));
                    ContactApp.record(tempList.get(index));
                    break;
            }
        }
    }

    public int getCount() {
        return contactList.size();
    }
}