package domain;

public class Name {

    private String title;
    private String firstName;
    private String middleName;
    private String surname;

    public Name() {
    }

    public Name(String title, String firstName, String middleName, String surname) {
        super();
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
