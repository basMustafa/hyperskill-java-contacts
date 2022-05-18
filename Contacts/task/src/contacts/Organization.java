package contacts;

import java.time.LocalDateTime;

public class Organization extends Contact {

    private String name;
    private String address;

    private Organization(String number, String name, String address) {
        super(number);
        this.name = name;
        this.address = address;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public void selectField() {
        System.out.println("Select a field (name, address, number):");
    }

    @Override
    public void editField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                setLastEditTime(LocalDateTime.now());
                return;
            case "address":
                setAddress(value);
                setLastEditTime(LocalDateTime.now());
                return;
            case "number":
                setNumber(value);
                setLastEditTime(LocalDateTime.now());
        }
    }

    @Override
    public boolean checkField(String field) {
        return "name".equals(field) || "address".equals(field) || "number".equals(field);
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s%n" +
                        "Address: %s%n" +
                        "Number: %s%n" +
                        "Time created: %s%n" +
                        "Time last edit: %s%n", name, address,
                "".equals(getNumber()) ? "[no data]" : getNumber(), getCreationTime(), getLastEditTime());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    static class Builder {

        private String number;
        private String name;
        private String address;

        Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        Contact build() {
            return new Organization(number, name, address);
        }
    }
}