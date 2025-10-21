package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.ModelStub;

public class AddTagCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));

        CommandResult commandResult = new AddTagCommand(validTag).execute(modelStub);

        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, validTag),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag validTag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        AddTagCommand addTagCommand = new AddTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class, "This tag already exists in the address book", () ->
                addTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag friends = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        Tag coworkers = new Tag(new TagName("Coworkers"), new TagDesc("Office mates"), new TagColor("0000FF"));
        AddTagCommand addFriendsCommand = new AddTagCommand(friends);
        AddTagCommand addCoworkersCommand = new AddTagCommand(coworkers);

        // same object -> returns true
        assertTrue(addFriendsCommand.equals(addFriendsCommand));

        // same values -> returns true
        AddTagCommand addFriendsCommandCopy = new AddTagCommand(friends);
        assertTrue(addFriendsCommand.equals(addFriendsCommandCopy));

        // different types -> returns false
        assertFalse(addFriendsCommand.equals(1));

        // null -> returns false
        assertFalse(addFriendsCommand.equals(null));

        // different tag -> returns false
        assertFalse(addFriendsCommand.equals(addCoworkersCommand));
    }

    @Test
    public void toStringMethod() {
        Tag tag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        AddTagCommand addTagCommand = new AddTagCommand(tag);
        String expected = AddTagCommand.class.getCanonicalName() + "{toAdd=" + tag + "}";
        assertEquals(expected, addTagCommand.toString());
    }

    /**
     * A Model stub that contains a single tag.
     */
    private class ModelStubWithTag extends ModelStub {

        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.isSameTag(tag);
        }
    }

    /**
     * A Model stub that always accepts the tag being added.
     */
    private class ModelStubAcceptingTagAdded extends ModelStub {

        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
