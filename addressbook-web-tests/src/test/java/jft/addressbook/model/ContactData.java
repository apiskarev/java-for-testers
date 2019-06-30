package jft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;
    @Transient
    private String allEmails;

    @Transient
    private String group;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

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

    public ContactData withThirdEmail(String thirdEmail){
        this.thirdEmail = thirdEmail;
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

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmails(){
        return allEmails;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", address='" + address + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                ", secondEmail='" + secondEmail + '\'' +
                ", thirdEmail='" + thirdEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData data = (ContactData) o;
        return id == data.id &&
                Objects.equals(firstName, data.firstName) &&
                Objects.equals(lastName, data.lastName) &&
                Objects.equals(homePhone, data.homePhone) &&
                Objects.equals(mobilePhone, data.mobilePhone) &&
                Objects.equals(workPhone, data.workPhone) &&
                Objects.equals(address, data.address) &&
                Objects.equals(firstEmail, data.firstEmail) &&
                Objects.equals(secondEmail, data.secondEmail) &&
                Objects.equals(thirdEmail, data.thirdEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, homePhone, mobilePhone, workPhone, address, firstEmail, secondEmail, thirdEmail);
    }
}
