package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;

/**
 * Deletes a Tag identified by its id.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deletetag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the tag identified by its ID.\n"
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_SUCCESS = "Deleted Tag: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "No tag found with the specified ID.";

    private final Id idToDelete;

    public DeleteTagCommand(Id id) {
        this.idToDelete = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Tag> tags = model.getFilteredTagList();
        Tag target = tags.stream()
                .filter(tag -> tag.getId().equals(idToDelete))
                .findFirst()
                .orElseThrow(() -> new CommandException(MESSAGE_TAG_NOT_FOUND));

        model.deleteTag(target);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        // instanceof handles null
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return idToDelete.equals(otherDeleteTagCommand.idToDelete);
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{idToDelete=" + idToDelete + "}";
    }
}
