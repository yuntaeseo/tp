package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RELATIONSHIPS;

import seedu.address.model.Model;

/**
 * Lists all relationships in the address book to the user.
 */
public class ListRelationshipCommand extends Command {

    public static final String COMMAND_WORD = "listrel";
    public static final String MESSAGE_SUCCESS = "Listed all relationships";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRelationshipList(PREDICATE_SHOW_ALL_RELATIONSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
