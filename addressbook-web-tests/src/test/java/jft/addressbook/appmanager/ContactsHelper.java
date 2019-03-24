package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsHelper extends HelperBase{

    ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void makeNewContact(ContactData data) {
        initNewContact();
        fillContact(data);
        saveContact();
        returnToContactList();
    }

    private void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
    }

    private void initNewContact() {
       click(By.linkText("add new"));
    }

    public void selectContact() {
        if (isElementPresent(By.cssSelector("tr[name=entry]"))){
            click(By.name("selected[]"));
        } else {
            makeNewContact(new ContactData("John","R.","Smith",
                    "Wall street, 19","+7 800 997 14 15", "john.smith@oracle.org"));
            click(By.name("selected[]"));
        }

    }

    public void editContact() {
        if (isElementPresent(By.cssSelector("a[href*='edit.php?']"))){
            click(By.cssSelector("a[href*='edit.php?']"));
        } else {
            makeNewContact(new ContactData("Wade","M.","Powers",
                    "Broadway street, 14","+7 800 964 11 17","powers@void.net"));
            click(By.cssSelector("a[href*='edit.php?']"));
        }
    }

    public void updateContact() {
        click(By.name("update"));
        returnToContactList();
    }

    private void saveContact(){
        click(By.name("submit"));
    }

    public void returnToContactList() {
        click(By.linkText("home page"));
    }

    public void contactDelete(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
    }


}
