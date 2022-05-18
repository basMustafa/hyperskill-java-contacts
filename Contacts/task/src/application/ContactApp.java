package application;

import contacts.PhoneBook;

import java.util.Scanner;

public class ContactApp {
    private static final PhoneBook phoneBook = new PhoneBook();
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        System.out.println("\n[menu] Enter action (add, list, search, count, exit):");
        String select = scanner.nextLine();

        switch (select) {
            case "add":
                phoneBook.addContact();
                break;
            case "list":
                phoneBook.listContacts();
                break;
            case "search":
                phoneBook.search();
                break;
            case "count":
                phoneBook.countContacts();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                start();
                break;
        }
    }

    public static void list() {
        System.out.println("\n[list] Enter action ([number], back):");
        String select = scanner.nextLine();

        if (isNumeric(select)) {
            int index = Integer.parseInt(select);
            if (index > phoneBook.getCount()) {
                System.out.printf("Only %d record(s) available", phoneBook.getCount());
                list();
            } else {
                index--;
                phoneBook.getInfo(index);
                record(index);
            }
        } else if ("back".equals(select)) {
            start();
        } else {
            list();
        }
    }

    public static void record(int index) {
        System.out.println("[record] Enter action (edit, delete, menu):");
        String select = scanner.nextLine();

        switch (select) {
            case "edit":
                phoneBook.editContact(index);
                break;
            case "delete":
                phoneBook.removeContact(index);
                start();
                break;
            case "menu":
                ContactApp.start();
                break;
            default:
                record(index);
                break;
        }
    }

    public static boolean isNumeric(String select) {
        try {
            int index = Integer.parseInt(select);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}