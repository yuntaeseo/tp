package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to the address book.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the address book. "
            + "Parameters: "
            + "n/NAME d/DESCRIPTION c/RGB_COLOR\n"
            + "Example: " + COMMAND_WORD + " n/Friends d/Schoolmates c/DF3C5F";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";

    // Tag object to perform checks on
    private final Tag toAddDummy;

    /**
     * Creates AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Tag tag) {
        requireNonNull(tag);
        toAddDummy = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAddDummy)) {
            throw new CommandException("This tag already exists in the address book");
        }

        // All checks pass, create the actual Tag object with a fresh ID
        Tag toAdd = new Tag(toAddDummy);
        model.addTag(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // same object
                || (other instanceof AddTagCommand // same type
                && toAddDummy.equals(((AddTagCommand) other).toAddDummy));
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{toAdd=" + toAddDummy + "}";
    }
}
