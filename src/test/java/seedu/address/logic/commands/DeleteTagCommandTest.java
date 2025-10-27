package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Color;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.ModelStub;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {

    private ModelStubWithTags modelStub;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setup() {
        tag1 = new Tag(new Id(1), new TagName("Friends"), new TagDesc("Schoolmates"), new Color("0000FF"));
        tag2 = new Tag(new Id(2), new TagName("Work"), new TagDesc("Office mates"), new Color("0000FF"));
        tag3 = new Tag(new Id(3), new TagName("Family"), new TagDesc("Close relatives"), new Color("0000FF"));
        modelStub = new ModelStubWithTags(List.of(tag1, tag2, tag3));
    }

    @Test
    public void execute_validId_success() throws Exception {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(new Id(2));
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_SUCCESS, tag2);

        ModelStubWithTags expectedModel = new ModelStubWithTags(List.of(tag1, tag3));

        assertCommandSuccess(deleteTagCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(new Id(99));

        java.util.List<Tag> before = List.copyOf(modelStub.getFilteredTagList());

        seedu.address.logic.commands.exceptions.CommandException ex =
                assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () ->
                deleteTagCommand.execute(modelStub));
        assertEquals(DeleteTagCommand.MESSAGE_TAG_NOT_FOUND, ex.getMessage());

        assertEquals(before, modelStub.getFilteredTagList());
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteFirstCommand = new DeleteTagCommand(new Id(1));
        DeleteTagCommand deleteSecondCommand = new DeleteTagCommand(new Id(2));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTagCommand deleteFirstCommandCopy = new DeleteTagCommand(new Id(1));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different ID -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Id id = new Id(1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(id);
        String expected = DeleteTagCommand.class.getCanonicalName() + "{idToDelete=" + id + "}";
        assertEquals(expected, deleteTagCommand.toString());
    }

    // -------------------------------------------------------------------------
    // Model Stub for Tags
    // -------------------------------------------------------------------------
    private static class ModelStubWithTags extends ModelStub {

        private final ObservableList<Tag> tags = FXCollections.observableArrayList();
        private final seedu.address.model.AddressBook backingAddressBook = new seedu.address.model.AddressBook();

        ModelStubWithTags(List<Tag> initialTags) {
            tags.addAll(initialTags);
            initialTags.forEach(backingAddressBook::addTag);
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            return tags;
        }

        @Override
        public void deleteTag(Tag tag) {
            tags.remove(tag);
            backingAddressBook.removeTag(tag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return backingAddressBook;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelStubWithTags)) {
                return false;
            }
            ModelStubWithTags that = (ModelStubWithTags) other;
            return this.tags.equals(that.tags);
        }
    }
}
