package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Id(1), new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet(1), new Note("Cool guy")),
            new Person(new Id(2), new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet(2, 1), new Note("I am Yu")),
            new Person(new Id(3), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet(3), new Note("From North Carolina")),
            new Person(new Id(4), new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet(4), new Note("Daveeeee")),
            new Person(new Id(5), new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet(5), new Note("Chill guy")),
            new Person(new Id(6), new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet(2), new Note("Roy: A Life Well Lived"))
        };
    }

    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag(new Id(1), new TagName("friends"), new TagDesc("Not so close friends"), new TagColor("03C04A")),
            new Tag(new Id(2), new TagName("colleagues"), new TagDesc("Enemies"), new TagColor("EDC001")),
            new Tag(new Id(3), new TagName("neighbours"), new TagDesc("Good people"), new TagColor("0080FE")),
            new Tag(new Id(4), new TagName("family"), new TagDesc("Love"), new TagColor("FF32FE")),
            new Tag(new Id(5), new TagName("exgirlfriend"), new TagDesc("Still have chance"), new TagColor("FF3333")),
        };
    }

    public static Relationship[] getSampleRelationships() {
        return new Relationship[] {
            new Relationship(new Id(1), new Id(2), new Description("friends from childhood")),
            new Relationship(new Id(2), new Id(3), new Description("classmates from primary school")),
            new Relationship(new Id(3), new Id(4), new Description("coworkers from Apple")),
            new Relationship(new Id(1), new Id(3), new Description("aunt Minh's family")),
            new Relationship(new Id(1), new Id(4), new Description("ex girlfriend")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Tag tag : getSampleTags()) {
            sampleAb.addTag(tag);
        }

        for (Relationship relationship : getSampleRelationships()) {
            sampleAb.addRelationship(relationship);
        }

        return sampleAb;
    }

    /**
     * Returns a set of tag IDs containing the list of strings given.
     */
    public static Set<Id> getTagSet(Integer... ids) {
        return Arrays.stream(ids)
                .map(Id::new)
                .collect(Collectors.toSet());
    }

}
