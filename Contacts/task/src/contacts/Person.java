package contacts;

import java.time.LocalDateTime;

public class Person extends Contact {

    private String name;
    private String surName;
    private Gender gender;
    private String birthDate;

    private Person(String number, String name, String surName, Gender gender, String birthDate) {
        super(number);
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String getFullName() {
        return name + " " + surName;
    }

    @Override
    public void selectField() {
        System.out.println("Select a field (name, surname, birth, gender, number):");
    }

    @Override
    public void editField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                setLastEditTime(LocalDateTime.now());
                return;
            case "surname":
                setSurName(value);
                setLastEditTime(LocalDateTime.now());
                return;
            case "birth":
                setBirthDate(value);
                setLastEditTime(LocalDateTime.now());
                return;
            case "gender":
                if ("M".equals(value)) {
                    setGender(Gender.MALE);
                } else if ("F".equals(value)) {
                    setGender(Gender.FEMALE);
                } else {
                    System.out.println("No such gender.");
                    return;
                }
                setLastEditTime(LocalDateTime.now());
                return;
            case "number":
                setNumber(value);
                setLastEditTime(LocalDateTime.now());
        }
    }

    @Override
    public boolean checkField(String field) {
        return "name".equals(field) || "surname".equals(field) || "birth".equals(field)
                || "gender".equals(field) || "number".equals(field);
    }

    @Override
    public String toString() {
        return String.format("Name: %s%n" +
                        "Surname: %s%n" +
                        "Birth date: %s%n" +
                        "Gender: %s%n" +
                        "Number: %s%n" +
                        "Time created: %s%n" +
                        "Time last edit: %s%n", name, surName, "".equals(birthDate) ? "[no data]" : birthDate,
                gender == null ? "[no data]" : getGender() == Gender.MALE ? "M" : "F",
                "".equals(getNumber()) ? "[no data]" : getNumber(), getCreationTime(), getLastEditTime());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    static class Builder {

        private String number;
        private String name;
        private String surName;
        private Gender gender;
        private String birthDate;

        Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setSurName(String surName) {
            this.surName = surName;
            return this;
        }

        Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        Builder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        Contact build() {
            return new Person(number, name, surName, gender, birthDate);
        }
    }
}