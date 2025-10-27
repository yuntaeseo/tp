package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "listtag";

    public static final String MESSAGE_SUCCESS = "Listed all tags";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert model.getTagList() != null : "Tag list doesn't exist.";
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        assert model.getFilteredTagList() != null : "Filtered tag list isn't available after update.";
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
