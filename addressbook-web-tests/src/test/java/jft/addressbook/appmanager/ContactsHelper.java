package jft.addressbook.appmanager;

import jft.addressbook.model.ContactData;
import jft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactsHelper extends HelperBase{

    ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
    }

    public void initNewContact() {
       click(By.linkText("add new"));
    }

    public void edit(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    }

    public void updateContact() {
        click(By.name("update"));
        returnToContactList();
    }

    public void saveContact(){
        click(By.name("submit"));
        returnToContactList();
    }

    private void returnToContactList() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        return isElementPresent(By.cssSelector("a[href*='edit.php?']"));
    }

    public void create(ContactData data) {
        initNewContact();
        fillContact(data);
        saveContact();

    }

    public void modify(ContactData contact) {
        fillContact(contact);
        updateContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteGroup();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='"+ id +"']")).click();
    }

    private void deleteGroup(){
        click(By.cssSelector("input[value='Delete']"));
        if (isAlertPresent()) wd.switchTo().alert().accept();
        returnToContactList();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> tableCells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = tableCells.get(1).getText();
            String firstName = tableCells.get(2).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }


}
