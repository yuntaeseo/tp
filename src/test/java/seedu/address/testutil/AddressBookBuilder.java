package seedu.address.testutil;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTag(Tag tag) {
        addressBook.addTag(tag);
        return this;
    }

    /**
     * Adds multiple {@code Tag}s to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTags(List<Tag> tags) {
        tags.forEach(addressBook::addTag);
        return this;
    }

    /**
     * Adds a new {@code Relationship} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withRelationship(Relationship relationship) {
        addressBook.addRelationship(relationship);
        return this;
    }

    /**
     * Adds multiple {@code Relationship}s to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withRelationships(List<Relationship> relationships) {
        relationships.forEach(addressBook::addRelationship);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
