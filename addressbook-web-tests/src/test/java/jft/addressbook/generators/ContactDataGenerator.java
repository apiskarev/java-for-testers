package jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveContactsAsJson(contacts, new File(file));
        } else System.out.println("Unrecognized format " + format);
    }

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void saveContactsAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gsonC = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gsonC.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData()
                            .withFirstName(String.format("firstName %s", i))
                            .withLastName(String.format("lastName %s", i))
                            .withHomePhone(String.format("+650 65 54 %s", i))
                            .withMobilePhone(String.format("+(99)66 421 564 %s", i))
                            .withWorkPhone(String.format("9851 3212 132%s", i))
                            .withAddress(String.format("Wall Stret 19, flat %s", i))
                            .withFirstEmail(String.format("john%s@smith.org", i))
                            .withSecondEmail(String.format("j.smith198%s@gmail.com", i))
                            .withThirdEmail(String.format("john198%s@gmail.com", i))
            );
        }
        return contacts;
    }
}
