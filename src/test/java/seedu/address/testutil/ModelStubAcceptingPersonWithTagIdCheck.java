package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A Model stub that always accept the person being added with checks for valid tag IDs.
 */
public class ModelStubAcceptingPersonWithTagIdCheck extends ModelStub {
    final ArrayList<Person> personsAdded = new ArrayList<>();
    final ArrayList<Tag> tagsAdded = new ArrayList<>();

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personsAdded.stream().anyMatch(person::isSamePerson);
    }

    @Override
    public void addPerson(Person person) {
        requireNonNull(person);
        personsAdded.add(person);
    }

    @Override
    public void addTag(Tag tag) {
        requireNonNull(tag);
        tagsAdded.add(tag);
    }

    @Override
    public boolean hasTagId(Id id) {
        return tagsAdded.stream().anyMatch(tag -> tag.getId().equals(id));
    }

    @Override
    public boolean hasTagIds(Collection<Id> ids) {
        return ids.stream().allMatch(this::hasTagId);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
