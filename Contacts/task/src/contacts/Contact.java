package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact {

    private final LocalDateTime creationTime;
    private LocalDateTime lastEditTime;
    private String number;

    public Contact(String number) {
        this.creationTime = LocalDateTime.now();
        this.lastEditTime = this.creationTime;
        setNumber(number);
    }

    public abstract String getFullName();

    public abstract void selectField();

    public abstract void editField(String field, String value);

    public abstract boolean checkField(String field);

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public String getNumber() {
        return number;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setNumber(String number) {
        if (checkNumber(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "";
        }
    }

    private boolean checkNumber(String number) {
        String regex = "^(\\+*\\(+\\w+\\)+([\\s-]+\\w{2,})?|\\+*\\w+([\\s-]+\\(*\\w{2,}\\)*)?)([\\s-]\\w{2,})*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}