package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {
    public static final Tag FRIENDS = new TagBuilder()
            .withId(0).withName("friends").withDesc("close people").withColor("AABBCC").build();
    public static final Tag ACQUAINTANCES = new TagBuilder()
            .withId(1).withName("acquaintances").withDesc("not close people").withColor("BBCCDD").build();
    public static final Tag COWORKERS = new TagBuilder()
            .withId(2).withName("coworkers").withDesc("definitely not close people").withColor("CCDDEE").build();
    public static final Tag CLASSMATES = new TagBuilder()
            .withId(3).withName("classmates").withDesc("close close people").withColor("DDEEFF").build();
    public static final Tag EX_GIRLFRIEND = new TagBuilder()
            .withId(4).withName("ex girlfriends").withDesc("not getting back").withColor("112233").build();

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tags.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(FRIENDS, ACQUAINTANCES, COWORKERS, CLASSMATES, EX_GIRLFRIEND));
    }
}
