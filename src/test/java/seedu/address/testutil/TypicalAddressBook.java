package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing an address book to be used in tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {}

    /**
     * Returns an {@code AddressBook} with all the typical persons, tags and relationships.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Tag tag : TypicalTags.getTypicalTags()) {
            ab.addTag(tag);
        }
        for (Relationship relationship : TypicalRelationships.getTypicalRelationships()) {
            ab.addRelationship(relationship);
        }
        return ab;
    }
}
