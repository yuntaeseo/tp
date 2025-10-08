package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "listtag";

    public static final String MESSAGE_SUCCESS = "Listed all tags";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

