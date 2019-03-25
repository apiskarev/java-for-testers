package jft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;

    public ContactData(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
