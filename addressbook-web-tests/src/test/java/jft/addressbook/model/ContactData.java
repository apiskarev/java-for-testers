package jft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String lastName;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String address;
    private String firstEmail;
    private String secondEmail;
    private String allEmails;
    private File photo;

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail){
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withAllEmails(String allEmails){
        this.allEmails = allEmails;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhone(){
        return homePhone;
    }

    public String getmobilePhone(){
        return mobilePhone;
    }

    public String getworkPhone(){
        return workPhone;
    }

    public String getAllPhones(){
        return allPhones;
    }

    public String getAddress(){
        return address;
    }

    public String getFirstEmail(){
        return firstEmail;
    }

    public String getSecondEmail(){
        return secondEmail;
    }

    public String getAllEmails(){
        return allEmails;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData data = (ContactData) o;
        return id == data.id &&
                Objects.equals(firstName, data.firstName) &&
                Objects.equals(lastName, data.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }


}
