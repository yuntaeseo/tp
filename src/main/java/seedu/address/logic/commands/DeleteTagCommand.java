package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;


public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deletetag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the tag identified by its ID.\n"
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 2";

    private final int idToDelete;

    public DeleteTagCommand(int id) {
        this.idToDelete = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Tag> tags = model.getFilteredTagList();
        Tag target = tags.stream()
                .filter(tag -> tag.getId() == idToDelete)
                .findFirst()
                .orElseThrow(() -> new CommandException("No tag found with ID: " + idToDelete));

        model.deleteTag(target);
        return new CommandResult(String.format("Deleted Tag: %s", target));
    }
}
